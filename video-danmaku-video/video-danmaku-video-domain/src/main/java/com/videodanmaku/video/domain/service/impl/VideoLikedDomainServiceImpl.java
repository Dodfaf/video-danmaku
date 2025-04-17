package com.videodanmaku.video.domain.service.impl;

import com.videodanmaku.video.domain.entity.VideoLikedBO;
import com.videodanmaku.video.domain.service.VideoLikedDomainService;
import com.videodanmaku.video.infra.basic.entity.VideoInfo;
import com.videodanmaku.video.infra.basic.entity.VideoLiked;
import com.videodanmaku.video.infra.basic.mapper.VideoInfoDao;
import com.videodanmaku.video.infra.basic.mapper.VideoLikedDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 视频点赞服务实现
 */
@Service
@Slf4j
public class VideoLikedDomainServiceImpl implements VideoLikedDomainService {

    // Redis key前缀
    private static final String VIDEO_LIKED_KEY = "video:liked:";
    private static final String VIDEO_LIKED_COUNT_KEY = "video:liked:count:";
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    @Resource
    private VideoLikedDao videoLikedDao;
    
    @Resource
    private VideoInfoDao videoInfoDao;

    @Override
    public Boolean likeVideo(Long videoId, Long userId) {
        String likedKey = VIDEO_LIKED_KEY + videoId;
        String countKey = VIDEO_LIKED_COUNT_KEY + videoId;
        
        // 检查当前点赞状态
        Boolean isLiked = redisTemplate.opsForSet().isMember(likedKey, userId);
        
        if (Boolean.TRUE.equals(isLiked)) {
            // 已点赞，取消点赞
            redisTemplate.opsForSet().remove(likedKey, userId);
            redisTemplate.opsForValue().decrement(countKey);
            return false;
        } else {
            // 未点赞，添加点赞
            redisTemplate.opsForSet().add(likedKey, userId);
            // 设置过期时间，防止内存占用过大
            redisTemplate.expire(likedKey, 7, TimeUnit.DAYS);
            
            // 增加点赞计数
            Long count = redisTemplate.opsForValue().increment(countKey);
            if (count == 1) {
                // 如果是第一次设置，设置过期时间
                redisTemplate.expire(countKey, 7, TimeUnit.DAYS);
            }
            return true;
        }
    }

    @Override
    public Boolean getLikeStatus(Long videoId, Long userId) {
        // 先从Redis中查询
        String likedKey = VIDEO_LIKED_KEY + videoId;
        Boolean isLiked = redisTemplate.opsForSet().isMember(likedKey, userId);
        
        if (isLiked != null) {
            return isLiked;
        }
        
        // Redis中没有，从数据库查询
        VideoLiked videoLiked = new VideoLiked();
        videoLiked.setVideoId(videoId.intValue());
        videoLiked.setLikeUserId(userId.intValue());
        videoLiked.setStatus(1); // 已点赞状态
        videoLiked.setIsDeleted(0); // 未删除
        
        Long count = videoLikedDao.count(videoLiked);
        
        // 如果数据库中有记录，同步到Redis
        if (count > 0) {
            redisTemplate.opsForSet().add(likedKey, userId);
            redisTemplate.expire(likedKey, 7, TimeUnit.DAYS);
            return true;
        }
        
        return false;
    }

    @Override
    public Long getLikeCount(Long videoId) {
        // 先从Redis中获取
        String countKey = VIDEO_LIKED_COUNT_KEY + videoId;
        Object count = redisTemplate.opsForValue().get(countKey);
        
        if (count != null) {
            return Long.valueOf(count.toString());
        }
        
        // Redis中没有，从数据库查询
        VideoInfo videoInfo = videoInfoDao.queryById(videoId.intValue());
        if (videoInfo != null && videoInfo.getLikes() != null) {
            // 将数据库中的点赞数同步到Redis
            redisTemplate.opsForValue().set(countKey, videoInfo.getLikes(), 7, TimeUnit.DAYS);
            return videoInfo.getLikes().longValue();
        }
        
        return 0L;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncLikeDataToDb() {
        log.info("开始同步Redis点赞数据到数据库");
        
        // 获取所有视频点赞key
        Set<String> likeKeys = redisTemplate.keys(VIDEO_LIKED_KEY + "*");
        if (likeKeys == null || likeKeys.isEmpty()) {
            return;
        }
        
        for (String likeKey : likeKeys) {
            try {
                // 提取视频ID
                String videoIdStr = likeKey.substring(VIDEO_LIKED_KEY.length());
                Long videoId = Long.parseLong(videoIdStr);
                
                // 获取点赞用户集合
                Set<Object> userIds = redisTemplate.opsForSet().members(likeKey);
                if (userIds == null || userIds.isEmpty()) {
                    continue;
                }
                
                // 更新数据库中的点赞记录
                for (Object userIdObj : userIds) {
                    Long userId = Long.parseLong(userIdObj.toString());
                    
                    // 查询是否已存在记录
                    VideoLiked query = new VideoLiked();
                    query.setVideoId(videoId.intValue());
                    query.setLikeUserId(userId.intValue());
                    query.setIsDeleted(0);
                    Long count = videoLikedDao.count(query);
                    
                    if (count == 0) {
                        // 不存在则新增
                        VideoLiked videoLiked = new VideoLiked();
                        videoLiked.setVideoId(videoId.intValue());
                        videoLiked.setLikeUserId(userId.intValue());
                        videoLiked.setStatus(1);
                        videoLiked.setCreateTime(new Date());
                        videoLiked.setCreateBy(userId.toString());
                        videoLiked.setIsDeleted(0);
                        videoLikedDao.insert(videoLiked);
                    }
                }
                
                // 更新视频表中的点赞数
                String countKey = VIDEO_LIKED_COUNT_KEY + videoId;
                Object countObj = redisTemplate.opsForValue().get(countKey);
                if (countObj != null) {
                    Integer likeCount = Integer.parseInt(countObj.toString());
                    
                    VideoInfo videoInfo = videoInfoDao.queryById(videoId.intValue());
                    if (videoInfo != null) {
                        videoInfo.setLikes(likeCount);
                        videoInfoDao.update(videoInfo);
                    }
                }
            } catch (Exception e) {
                log.error("同步视频点赞数据异常: {}", e.getMessage(), e);
            }
        }
        
        log.info("同步Redis点赞数据到数据库完成");
    }
}