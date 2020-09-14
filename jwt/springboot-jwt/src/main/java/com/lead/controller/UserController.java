package com.lead.controller;

import com.lead.annotations.UserLoginToken;
import com.lead.bean.BaseResult;
import com.lead.entity.User;
import com.lead.service.UserService;
import com.lead.utils.JwtUtils;
import com.lead.utils.VerifyEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author mac
 */
@RestController
@RequestMapping("/users")
@Api(value = "用户controller", tags = {"用户操作接口"})
public class UserController {


    @Resource
    private UserService userService;


    @PostMapping("/login")
    @ApiOperation(value = "用户登录", tags = {"返回token信息"}, notes = "用户名和密码不能为空")
    public BaseResult login(@RequestBody User user) throws IllegalAccessException {
        VerifyEntity.verify(user);
        User frontUser = userService.verifyUser(user);
        //判断前台是否为空
        if (ObjectUtils.isEmpty(frontUser)) {
            throw new RuntimeException(String.format("此%s不存在，请从新登陆", frontUser.getName()));
        } else {
            if (!frontUser.getPassword().equals(user.getPassword())) {
                throw new RuntimeException("密码输入错误，请从新登陆");
            }
        }
        String token = JwtUtils.generateToken(frontUser.getName(), 7, frontUser.getPassword());
        return BaseResult.success(token);
    }

    @GetMapping("/exam")
    @ApiOperation(value = "token验证")
    @UserLoginToken
    public BaseResult check() {
        return BaseResult.success("通过检验");
    }

}
