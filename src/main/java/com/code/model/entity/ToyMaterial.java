package com.code.model.entity;

import java.util.Date;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;

/**
 * 玩具材质表(ToyMaterial)表实体类
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:09
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("toy_material")
@Accessors(chain = true)
public class ToyMaterial implements Serializable{
    @TableId(type = IdType.AUTO)
    private Integer id;

    //材质
    private String material;
    //创建时间
    private Date createTime;



}

