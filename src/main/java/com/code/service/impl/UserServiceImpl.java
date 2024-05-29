package com.code.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.code.model.entity.User;
import com.code.service.UserService;
import com.code.mapper.UserMapper;


/**
 * 用户表(User)表服务实现类
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:09
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

