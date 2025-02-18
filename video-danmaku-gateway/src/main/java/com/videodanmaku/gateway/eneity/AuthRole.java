package com.videodanmaku.gateway.eneity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class AuthRole implements Serializable {
    private static final long serialVersionUID = 422256240999600735L;
    
    private Long id;
    
    private String roleName;
    
    private String roleKey;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    
    private Integer isDeleted;



}