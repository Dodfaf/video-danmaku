package com.videodanmaku.video.application.convert;

import com.videodanmaku.video.application.dto.VideoInfoDTO;
import com.videodanmaku.video.domain.entity.VideoInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VideoInfoDTOConverter {

    VideoInfoDTOConverter INSTANCE = Mappers.getMapper(VideoInfoDTOConverter.class);

    VideoInfoBO convertDtoToBO(VideoInfoDTO subjectInfoDTO);
    VideoInfoDTO convertBoToDTO(VideoInfoBO subjectInfoBO);

}
