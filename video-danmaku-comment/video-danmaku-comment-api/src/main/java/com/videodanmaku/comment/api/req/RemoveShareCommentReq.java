package com.videodanmaku.comment.api.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 鸡圈内容信息
 * </p>
 */
@Getter
@Setter
public class RemoveShareCommentReq implements Serializable {

    private Long id;

    /**
     * 回复类型 1评论 2回复
     */
    private Integer replyType;

}
