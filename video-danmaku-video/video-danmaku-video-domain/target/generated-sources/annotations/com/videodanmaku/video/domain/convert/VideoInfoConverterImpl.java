package com.videodanmaku.video.domain.convert;

import com.videodanmaku.video.domain.entity.VideoInfoBO;
import com.videodanmaku.video.infra.basic.entity.VideoInfo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-07T20:45:49+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class VideoInfoConverterImpl implements VideoInfoConverter {

    @Override
    public VideoInfo convertBoToEntity(VideoInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        VideoInfo videoInfo = new VideoInfo();

        videoInfo.setId( subjectInfoBO.getId() );
        videoInfo.setVideoTitle( subjectInfoBO.getVideoTitle() );
        videoInfo.setUpId( subjectInfoBO.getUpId() );
        videoInfo.setVideoUrl( subjectInfoBO.getVideoUrl() );
        videoInfo.setStatus( subjectInfoBO.getStatus() );
        videoInfo.setCoverUrl( subjectInfoBO.getCoverUrl() );
        videoInfo.setLikes( subjectInfoBO.getLikes() );
        videoInfo.setFavorites( subjectInfoBO.getFavorites() );
        videoInfo.setCreateBy( subjectInfoBO.getCreateBy() );
        videoInfo.setCreateTime( subjectInfoBO.getCreateTime() );
        videoInfo.setUpdateBy( subjectInfoBO.getUpdateBy() );
        videoInfo.setUpdateTime( subjectInfoBO.getUpdateTime() );
        videoInfo.setIsDeleted( subjectInfoBO.getIsDeleted() );
        videoInfo.setDuration( subjectInfoBO.getDuration() );
        videoInfo.setDescription( subjectInfoBO.getDescription() );
        videoInfo.setViews( subjectInfoBO.getViews() );

        return videoInfo;
    }

    @Override
    public VideoInfoBO convertEntityToBO(VideoInfo subjectInfo) {
        if ( subjectInfo == null ) {
            return null;
        }

        VideoInfoBO videoInfoBO = new VideoInfoBO();

        videoInfoBO.setId( subjectInfo.getId() );
        videoInfoBO.setVideoTitle( subjectInfo.getVideoTitle() );
        videoInfoBO.setUpId( subjectInfo.getUpId() );
        videoInfoBO.setVideoUrl( subjectInfo.getVideoUrl() );
        videoInfoBO.setStatus( subjectInfo.getStatus() );
        videoInfoBO.setCoverUrl( subjectInfo.getCoverUrl() );
        videoInfoBO.setLikes( subjectInfo.getLikes() );
        videoInfoBO.setFavorites( subjectInfo.getFavorites() );
        videoInfoBO.setCreateBy( subjectInfo.getCreateBy() );
        videoInfoBO.setCreateTime( subjectInfo.getCreateTime() );
        videoInfoBO.setUpdateBy( subjectInfo.getUpdateBy() );
        videoInfoBO.setUpdateTime( subjectInfo.getUpdateTime() );
        videoInfoBO.setIsDeleted( subjectInfo.getIsDeleted() );
        videoInfoBO.setDuration( subjectInfo.getDuration() );
        videoInfoBO.setDescription( subjectInfo.getDescription() );
        videoInfoBO.setViews( subjectInfo.getViews() );

        return videoInfoBO;
    }
}
