package com.code.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.code.model.entity.Toy;
import com.code.service.ToyService;
import com.code.mapper.ToyMapper;


/**
 * 玩具表(Toy)表服务实现类
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:08
 */
@Service("toyService")
public class ToyServiceImpl extends ServiceImpl<ToyMapper, Toy> implements ToyService {

}

