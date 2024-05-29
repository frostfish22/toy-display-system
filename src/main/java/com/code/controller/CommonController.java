package com.code.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.code.common.Result;
import com.code.model.entity.User;
import com.code.service.UserService;
import com.code.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Api(tags = "前台用户模块")
@RestController
@RequestMapping("/web")
public class CommonController {

    @Resource
    private UserService userService;

    // 前台用户登录
    @ApiOperation(value = "前台用户登录")
    @PostMapping("/user/login")
    public Result UserLogin(@RequestBody User user) {
        // 根据用户名查询数据库
        User user1 = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (ObjectUtil.isEmpty(user1) || !user1.getPassword().equals(user.getPassword()) ){
            return Result.error("用户名或密码不正确");
        }
        //存入用户id，生成token，
        Map claims = new HashMap<String, Object>();
        claims.put("userId",user1.getId());
        String token = JwtUtils.createJWT(2, claims);
        user1.setToken(token);
        return Result.success(user1);
    }

    // 前台用户注册
    @ApiOperation(value = "前台用户注册")
    @PostMapping("/user/register")
    public Result UserRegister(@RequestBody User user) {
        // 根据用户名查询数据库
        User user1 = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (ObjectUtil.isNotEmpty(user1)){
            return Result.error("用户已存在");
        }
        userService.save(user);
        return Result.success();
    }

}
