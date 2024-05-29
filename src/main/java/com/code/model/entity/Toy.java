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
 * 玩具表(Toy)表实体类
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:08
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("toy")
@Accessors(chain = true)
public class Toy implements Serializable{
    @TableId(type = IdType.AUTO)
    private Integer id;

    //名称
    private String name;
    //图片
    private String image;
    private String cover;
    //类型ID
    private Integer typeId;
    //材质ID
    private Integer materialId;
    //品牌ID
    private Integer brandId;
    //详细类型
    private String detailedType;
    //适用年龄
    private String applicableAge;
    //款式
    private String style;
    //尺寸
    private String size;
    //价格
    private Double price;
    //详情
    private String details;
    //创建时间
    private Date createTime;



}

