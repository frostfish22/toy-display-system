package com.code.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.code.model.entity.ToyMaterial;
import com.code.service.ToyMaterialService;
import com.code.mapper.ToyMaterialMapper;


/**
 * 玩具材质表(ToyMaterial)表服务实现类
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:09
 */
@Service("toyMaterialService")
public class ToyMaterialServiceImpl extends ServiceImpl<ToyMaterialMapper, ToyMaterial> implements ToyMaterialService {

}

