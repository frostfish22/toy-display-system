package com.code.model.entity;

import java.util.Date;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;

/**
 * 用户表(User)表实体类
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:09
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@Accessors(chain = true)
public class User implements Serializable{
    @TableId(type = IdType.AUTO)
    private Integer id;

    //用户名
    private String username;
    //密码
    private String password;
    //头像
    private String avatar;
    //手机号
    private String phoneNumber;
    //年龄
    private Integer age;
    //性别
    private String gender;
    //加入时间
    private Date joinTime;

    @TableField(exist = false)
    private String token;



}

