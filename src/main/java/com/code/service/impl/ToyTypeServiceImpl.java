package com.code.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.code.model.entity.ToyType;
import com.code.service.ToyTypeService;
import com.code.mapper.ToyTypeMapper;


/**
 * 玩具类型表(ToyType)表服务实现类
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:09
 */
@Service("toyTypeService")
public class ToyTypeServiceImpl extends ServiceImpl<ToyTypeMapper, ToyType> implements ToyTypeService {

}

