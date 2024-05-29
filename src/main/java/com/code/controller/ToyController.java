package com.code.controller;



import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.model.entity.Toy;
import com.code.model.dto.ToyPageDto;
import com.code.service.ToyService;
import com.code.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 玩具表(Toy)表控制层
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:08
 */
@Api(tags = "玩具模块")
@RestController
@RequestMapping("toy")
public class ToyController {
    /**
     * 服务对象
     */
    @Resource
    private ToyService toyService;

    /**
     * 分页条件查询数据
    */
    @ApiOperation(value = "分页条件查询数据")
    @GetMapping
    public Result selectPage( ToyPageDto pageDto) {
        Page<Toy> page = new Page<>(pageDto.getPageNumber(), pageDto.getPageSize());
        LambdaQueryWrapper<Toy> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(pageDto.getName()),Toy::getName,pageDto.getName())
                .eq(ObjectUtil.isNotEmpty(pageDto.getBrandId()),Toy::getBrandId,pageDto.getBrandId())
                .eq(ObjectUtil.isNotEmpty(pageDto.getMaterialId()),Toy::getMaterialId,pageDto.getMaterialId())
                .eq(ObjectUtil.isNotEmpty(pageDto.getTypeId()),Toy::getTypeId,pageDto.getTypeId());
        page = toyService.page(page, wrapper);
        return Result.success(Dict.create().set("dataList", page.getRecords()).set("total",page.getTotal()));
    }

    /**
     * 通过id查询单条数据
     *
     * @param id 主键
     * @return
     */
    @ApiOperation(value = "通过id查询单条数据")
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Integer id) {
        return  Result.success(toyService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param toy 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public Result insert(@RequestBody Toy toy) {
        return  Result.success(toyService.save(toy));
    }

    /**
     * 修改数据
     *
     * @param toy 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public Result update(@RequestBody Toy toy) {
        return  Result.success(toyService.updateById(toy));
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @ApiOperation(value = "删除数据")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        return  Result.success(toyService.removeById(id));
    }
    // 批量删除
    @ApiOperation(value = "批量删除")
    @DeleteMapping("/deleteBatch")
    public Result delete(@RequestBody List<Integer> idList) {
        return  Result.success(toyService.removeByIds(idList));
    }
    /**
     * 查询所有数据
    */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/getAll")
    public Result getAll( ToyPageDto pageDto) {
        List<Toy> list  = toyService.list();
        return Result.success(list);
    }
}

