package com.code.controller;



import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.model.entity.ToyBrand;
import com.code.model.dto.ToyBrandPageDto;
import com.code.service.ToyBrandService;
import com.code.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 玩具品牌表(ToyBrand)表控制层
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:08
 */
@Api(tags = "玩具品牌模块")
@RestController
@RequestMapping("toyBrand")
public class ToyBrandController {
    /**
     * 服务对象
     */
    @Resource
    private ToyBrandService toyBrandService;

    /**
     * 分页条件查询数据
    */
    @ApiOperation(value = "分页条件查询数据")
    @GetMapping
    public Result selectPage( ToyBrandPageDto pageDto) {
        Page<ToyBrand> page = new Page<>(pageDto.getPageNumber(), pageDto.getPageSize());
        LambdaQueryWrapper<ToyBrand> wrapper = new LambdaQueryWrapper<>();
        //wrapper.like(ObjectUtil.isNotEmpty(pageDto.getUsername()),Entity::getUsername,pageDto.getUsername());
        page = toyBrandService.page(page, wrapper);
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
        return  Result.success(toyBrandService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param toyBrand 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public Result insert(@RequestBody ToyBrand toyBrand) {
        return  Result.success(toyBrandService.save(toyBrand));
    }

    /**
     * 修改数据
     *
     * @param toyBrand 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public Result update(@RequestBody ToyBrand toyBrand) {
        return  Result.success(toyBrandService.updateById(toyBrand));
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @ApiOperation(value = "删除数据")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        return  Result.success(toyBrandService.removeById(id));
    }
    // 批量删除
    @ApiOperation(value = "批量删除")
    @DeleteMapping("/deleteBatch")
    public Result delete(@RequestBody List<Integer> idList) {
        return  Result.success(toyBrandService.removeByIds(idList));
    }
    /**
     * 查询所有数据
    */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/getAll")
    public Result getAll( ToyBrandPageDto pageDto) {
        List<ToyBrand> list  = toyBrandService.list();
        return Result.success(list);
    }
}

