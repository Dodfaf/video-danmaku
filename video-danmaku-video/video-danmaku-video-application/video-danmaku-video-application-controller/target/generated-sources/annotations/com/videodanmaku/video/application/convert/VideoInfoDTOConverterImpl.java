package com.videodanmaku.video.application.convert;

import com.videodanmaku.video.application.dto.VideoInfoDTO;
import com.videodanmaku.video.domain.entity.VideoInfoBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-03T17:11:30+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class VideoInfoDTOConverterImpl implements VideoInfoDTOConverter {

    @Override
    public VideoInfoBO convertDtoToBO(VideoInfoDTO subjectInfoDTO) {
        if ( subjectInfoDTO == null ) {
            return null;
        }

        VideoInfoBO videoInfoBO = new VideoInfoBO();

        videoInfoBO.setId( subjectInfoDTO.getId() );
        videoInfoBO.setVideoTitle( subjectInfoDTO.getVideoTitle() );
        videoInfoBO.setUpId( subjectInfoDTO.getUpId() );
        videoInfoBO.setVideoUrl( subjectInfoDTO.getVideoUrl() );
        videoInfoBO.setStatus( subjectInfoDTO.getStatus() );
        videoInfoBO.setCoverUrl( subjectInfoDTO.getCoverUrl() );
        videoInfoBO.setLikes( subjectInfoDTO.getLikes() );
        videoInfoBO.setFavorites( subjectInfoDTO.getFavorites() );
        videoInfoBO.setCreateBy( subjectInfoDTO.getCreateBy() );
        videoInfoBO.setCreateTime( subjectInfoDTO.getCreateTime() );
        videoInfoBO.setUpdateTime( subjectInfoDTO.getUpdateTime() );
        videoInfoBO.setIsDeleted( subjectInfoDTO.getIsDeleted() );
        videoInfoBO.setDuration( subjectInfoDTO.getDuration() );
        videoInfoBO.setDescription( subjectInfoDTO.getDescription() );
        videoInfoBO.setViews( subjectInfoDTO.getViews() );

        return videoInfoBO;
    }

    @Override
    public VideoInfoDTO convertBoToDTO(VideoInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        VideoInfoDTO videoInfoDTO = new VideoInfoDTO();

        videoInfoDTO.setId( subjectInfoBO.getId() );
        videoInfoDTO.setVideoTitle( subjectInfoBO.getVideoTitle() );
        videoInfoDTO.setUpId( subjectInfoBO.getUpId() );
        videoInfoDTO.setVideoUrl( subjectInfoBO.getVideoUrl() );
        videoInfoDTO.setStatus( subjectInfoBO.getStatus() );
        videoInfoDTO.setCoverUrl( subjectInfoBO.getCoverUrl() );
        videoInfoDTO.setLikes( subjectInfoBO.getLikes() );
        videoInfoDTO.setFavorites( subjectInfoBO.getFavorites() );
        videoInfoDTO.setCreateBy( subjectInfoBO.getCreateBy() );
        videoInfoDTO.setCreateTime( subjectInfoBO.getCreateTime() );
        videoInfoDTO.setUpdateTime( subjectInfoBO.getUpdateTime() );
        videoInfoDTO.setIsDeleted( subjectInfoBO.getIsDeleted() );
        videoInfoDTO.setViews( subjectInfoBO.getViews() );
        videoInfoDTO.setDescription( subjectInfoBO.getDescription() );
        videoInfoDTO.setDuration( subjectInfoBO.getDuration() );

        return videoInfoDTO;
    }
}
