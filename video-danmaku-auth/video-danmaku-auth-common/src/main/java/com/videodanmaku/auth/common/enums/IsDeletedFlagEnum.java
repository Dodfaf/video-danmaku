package com.videodanmaku.auth.common.enums;

import lombok.Getter;

@Getter
public enum IsDeletedFlagEnum {

    UN_DELETED(0,"未删除"),
    DELETED(1,"已删除");

    public int code;

    public String desc;

    IsDeletedFlagEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

}