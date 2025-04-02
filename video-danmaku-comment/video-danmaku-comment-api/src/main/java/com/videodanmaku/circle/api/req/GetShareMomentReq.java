package com.videodanmaku.circle.api.req;

import com.videodanmaku.circle.api.common.PageInfo;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetShareMomentReq implements Serializable {

    /**
     * 圈子ID
     */
    private Long videoId;

    /**
     * 分页信息
     */
    private PageInfo pageInfo;

}