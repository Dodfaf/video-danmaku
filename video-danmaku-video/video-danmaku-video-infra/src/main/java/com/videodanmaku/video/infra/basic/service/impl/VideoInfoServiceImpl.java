package com.videodanmaku.video.infra.basic.service.impl;

import com.videodanmaku.video.infra.basic.entity.VideoInfo;
import com.videodanmaku.video.infra.basic.mapper.VideoInfoDao;
import com.videodanmaku.video.infra.basic.service.VideoInfoService;
import org.mapstruct.ap.shaded.freemarker.core.ReturnInstruction;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频信息表(VideoInfo)表服务实现类
 *
 * @author makejava
 * @since 2025-02-22 21:08:27
 */
@Service("videoInfoService")
public class VideoInfoServiceImpl implements VideoInfoService {
    @Resource
    private VideoInfoDao videoInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VideoInfo queryById(Integer id) {
        return this.videoInfoDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param videoInfo 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VideoInfo> queryByPage(VideoInfo videoInfo, PageRequest pageRequest) {
        long total = this.videoInfoDao.count(videoInfo);
        return new PageImpl<>(this.videoInfoDao.queryAllByLimit(videoInfo, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param videoInfo 实例对象
     * @return 实例对象
     */
    @Override
    public VideoInfo insert(VideoInfo videoInfo) {
        this.videoInfoDao.insert(videoInfo);
        return videoInfo;
    }

    /**
     * 修改数据
     *
     * @param videoInfo 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(VideoInfo videoInfo) {
        return this.videoInfoDao.update(videoInfo);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.videoInfoDao.deleteById(id) > 0;
    }

    @Override
    public List<VideoInfo> queryByUpId(Integer upId) {
        return null;
    }

    @Override
    public List<VideoInfo> getRandom() {
        return this.videoInfoDao.getRandom();
    }
}
