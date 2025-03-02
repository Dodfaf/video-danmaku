package com.videodanmaku.video.domain.convert;

import com.videodanmaku.video.domain.entity.VideoInfoBO;
import com.videodanmaku.video.infra.basic.entity.VideoInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VideoInfoConverter {

    VideoInfoConverter INSTANCE = Mappers.getMapper(VideoInfoConverter.class);

    VideoInfo convertBoToEntity(VideoInfoBO subjectInfoBO);
    VideoInfoBO convertEntityToBO(VideoInfo subjectInfo);

}
