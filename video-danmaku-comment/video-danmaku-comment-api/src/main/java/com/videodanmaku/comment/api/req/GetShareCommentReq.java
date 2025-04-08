package com.videodanmaku.comment.api.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 内容信息
 * </p>

 */
@Getter
@Setter
public class GetShareCommentReq implements Serializable {

    private Long id;

}
