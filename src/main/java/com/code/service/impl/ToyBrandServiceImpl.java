package com.code.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.code.model.entity.ToyBrand;
import com.code.service.ToyBrandService;
import com.code.mapper.ToyBrandMapper;


/**
 * 玩具品牌表(ToyBrand)表服务实现类
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:08
 */
@Service("toyBrandService")
public class ToyBrandServiceImpl extends ServiceImpl<ToyBrandMapper, ToyBrand> implements ToyBrandService {

}

