package com.videodanmaku.video.application.cotroller;

import com.videodanmaku.common.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {


    @RequestMapping("/test")
    public Result<String> test(){

        return Result.ok("cheng");
    }

}
