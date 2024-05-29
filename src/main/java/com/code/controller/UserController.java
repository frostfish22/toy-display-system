package com.code.controller;



import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.model.entity.User;
import com.code.model.dto.UserPageDto;
import com.code.service.UserService;
import com.code.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户表(User)表控制层
 *
 * @author xiaoxin
 * @since 2024-04-02 15:56:09
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 分页条件查询数据
    */
    @ApiOperation(value = "分页条件查询数据")
    @GetMapping
    public Result selectPage( UserPageDto pageDto) {
        Page<User> page = new Page<>(pageDto.getPageNumber(), pageDto.getPageSize());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(pageDto.getUsername()),User::getUsername,pageDto.getUsername());
        page = userService.page(page, wrapper);
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
        return  Result.success(userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public Result insert(@RequestBody User user) {
        return  Result.success(userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public Result update(@RequestBody User user) {
        return  Result.success(userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @ApiOperation(value = "删除数据")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        return  Result.success(userService.removeById(id));
    }
    // 批量删除
    @ApiOperation(value = "批量删除")
    @DeleteMapping("/deleteBatch")
    public Result delete(@RequestBody List<Integer> idList) {
        return  Result.success(userService.removeByIds(idList));
    }
    /**
     * 查询所有数据
    */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/getAll")
    public Result getAll( UserPageDto pageDto) {
        List<User> list  = userService.list();
        return Result.success(list);
    }
}

