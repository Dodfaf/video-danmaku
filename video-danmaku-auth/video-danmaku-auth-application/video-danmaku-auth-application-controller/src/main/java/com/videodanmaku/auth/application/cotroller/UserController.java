package com.videodanmaku.auth.application.cotroller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.base.Preconditions;
import com.videodanmaku.auth.application.convert.AuthUserDTOConverter;
import com.videodanmaku.auth.application.dto.AuthUserDTO;
import com.videodanmaku.auth.domain.entity.AuthUserBO;
import com.videodanmaku.auth.domain.service.AuthUserDomainService;
import com.videodanmaku.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {
    @Resource
    private AuthUserDomainService authUserDomainService;

    @RequestMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.register(authUserBO));
        } catch (Exception e) {
            log.error("UserController.register.error:{}", e.getMessage(), e);
            return Result.fail("注册用户失败");
        }
    }
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新用户信息失败");
        }
    }

    /**
     * 批量获取用户信息
     */
    @RequestMapping("listByIds")
    public Result<List<AuthUserDTO>> listUserInfoByIds(@RequestBody List<String> userNameList) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.listUserInfoByIds.dto:{}", JSON.toJSONString(userNameList));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(userNameList), "id集合不能为空");
            List<AuthUserBO> userInfos = authUserDomainService.listUserInfoByIds(userNameList);
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBOToDTO(userInfos));
        } catch (Exception e) {
            log.error("UserController.listUserInfoByIds.error:{}", e.getMessage(), e);
            return Result.fail("批量获取用户信息失败");
        }
    }




    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public Result<Boolean> doLogin(@RequestBody AuthUserDTO authUserDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.doLogin.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "密码不能为空！");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.doLogin(authUserBO));
        } catch (Exception e) {
            log.error("UserController.doLogin.error:{}", e.getMessage(), e);
            return Result.fail("用户登录失败！");
        }

    }

    /**
     * 用户退出
     */
    @RequestMapping("logOut")
    public Result logOut(@RequestParam Integer id) {
        try {
//            log.info("UserController.logOut.userName:{}", userName);
//            Preconditions.checkArgument(!StringUtils.isBlank(userName), "用户名不能为空");
            StpUtil.logout(id);
            return Result.ok();
        } catch (Exception e) {
            log.error("UserController.logOut.error:{}", e.getMessage(), e);
            return Result.fail("用户登出失败");
        }
    }


    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    public void checkUserInfo(AuthUserDTO authUserDTO){
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空！");
    }

    @RequestMapping("getUserInfo")
    public Result<Boolean> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.getUserInfo.dto:{}", JSON.toJSONString(authUserDTO));
            }
//            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBOToDTO(userInfo));
        } catch (Exception e) {
            log.error("UserController.getUserInfo.error:{}", e.getMessage(), e);
            return Result.fail("获取用户信息失败！");
        }

    }
    @RequestMapping("changeUserStatus")
    public Result<Boolean> changeUserStatus(@RequestBody AuthUserDTO authUserDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.changeUserStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.changeUserStatus.error:{}", e.getMessage(), e);
            return Result.fail("更新用户状态失败！");
        }
    }
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {

        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.changeUserStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBO));
        } catch (Exception e) {
            log.error("UserController.changeUserStatus.error:{}", e.getMessage(), e);
            return Result.fail("删除用户失败！");
        }
    }


}
