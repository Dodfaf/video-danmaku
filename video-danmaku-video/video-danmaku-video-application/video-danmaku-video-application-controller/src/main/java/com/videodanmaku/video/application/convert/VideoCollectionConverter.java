package com.videodanmaku.video.application.convert;

import com.videodanmaku.video.application.dto.VideoCollectionFolderDTO;
import com.videodanmaku.video.application.dto.VideoCollectionRecordDTO;
import com.videodanmaku.video.domain.entity.VideoCollectionFolderBO;
import com.videodanmaku.video.domain.entity.VideoCollectionRecordBO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频收藏转换器
 */
public class VideoCollectionConverter {

    /**
     * BO转DTO
     */
    public static VideoCollectionFolderDTO toDTO(VideoCollectionFolderBO bo) {
        if (bo == null) {
            return null;
        }
        VideoCollectionFolderDTO dto = new VideoCollectionFolderDTO();
        BeanUtils.copyProperties(bo, dto);
        return dto;
    }

    /**
     * DTO转BO
     */
    public static VideoCollectionFolderBO toBO(VideoCollectionFolderDTO dto) {
        if (dto == null) {
            return null;
        }
        VideoCollectionFolderBO bo = new VideoCollectionFolderBO();
        BeanUtils.copyProperties(dto, bo);
        return bo;
    }

    /**
     * BO列表转DTO列表
     */
    public static List<VideoCollectionFolderDTO> toDTOList(List<VideoCollectionFolderBO> boList) {
        if (boList == null) {
            return null;
        }
        List<VideoCollectionFolderDTO> dtoList = new ArrayList<>(boList.size());
        for (VideoCollectionFolderBO bo : boList) {
            dtoList.add(toDTO(bo));
        }
        return dtoList;
    }

    /**
     * 收藏记录BO转DTO
     */
    public static VideoCollectionRecordDTO toDTO(VideoCollectionRecordBO bo) {
        if (bo == null) {
            return null;
        }
        VideoCollectionRecordDTO dto = new VideoCollectionRecordDTO();
        BeanUtils.copyProperties(bo, dto);
        return dto;
    }

    /**
     * 收藏记录DTO转BO
     */
    public static VideoCollectionRecordBO toBO(VideoCollectionRecordDTO dto) {
        if (dto == null) {
            return null;
        }
        VideoCollectionRecordBO bo = new VideoCollectionRecordBO();
        BeanUtils.copyProperties(dto, bo);
        return bo;
    }
}