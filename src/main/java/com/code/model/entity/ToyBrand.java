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
 * 玩具品牌表(ToyBrand)表实体类
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:08
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("toy_brand")
@Accessors(chain = true)
public class ToyBrand implements Serializable{
    @TableId(type = IdType.AUTO)
    private Integer id;

    //品牌名称
    private String brandName;
    //描述
    private String description;
    //创建时间
    private Date createTime;



}

