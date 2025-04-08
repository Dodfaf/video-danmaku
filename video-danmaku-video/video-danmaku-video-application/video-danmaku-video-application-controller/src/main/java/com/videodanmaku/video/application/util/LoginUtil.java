package com.videodanmaku.video.application.util;


import com.videodanmaku.video.application.context.LoginContextHolder;

/**
 * 用户登录util
 *
 */
public class LoginUtil {

    public static String getLoginId() {
        return LoginContextHolder.getLoginId();
    }


}
