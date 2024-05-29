package com.code.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.code.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 *  文件上传接口
 */
@Api(tags = "文件上传模块")
@RestController
@RequestMapping("/file")
public class FileController {

    // 文件上传存储路径
    private static final String filePath = System.getProperty("user.dir") + "/file/";
    private static final String http = "http://localhost:9090/file/";

    /**
     * 文件上传
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        synchronized (FileController.class) {
            // 获取当前时间戳
            String flag = System.currentTimeMillis() + "";
            // 获取原始文件名（就是你上传的文件本身的名字）
            String fileName = file.getOriginalFilename();
            try {
                // 如果没有file文件夹，会给你在项目根目录下创建一个file文件夹
                if (!FileUtil.isDirectory(filePath)) {
                    FileUtil.mkdir(filePath);
                }
                // 文件存储形式：时间戳-文件名
                FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
                System.out.println(fileName + "--上传成功");
                Thread.sleep(1L);
            } catch (Exception e) {
                System.err.println(fileName + "--文件上传失败");
            }
            return Result.success(http + flag + "-" + fileName);
        }
    }
    @ApiOperation(value = "富文本编辑器上传")
    @PostMapping("/editor/upload")
    public Dict editorUpload( @RequestParam MultipartFile file , @RequestParam String type) {
        synchronized (FileController.class) {
            // 获取当前时间戳
            String flag = System.currentTimeMillis() + "";
            // 获取原始文件名（就是你上传的文件本身的名字）
            String fileName = file.getOriginalFilename();
            try {
                // 如果没有file文件夹，会给你在项目根目录下创建一个file文件夹
                if (!FileUtil.isDirectory(filePath)) {
                    FileUtil.mkdir(filePath);
                }
                // 文件存储形式：时间戳-文件名
                FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
                System.out.println(fileName + "--上传成功");
                Thread.sleep(1L);
            } catch (Exception e) {
                System.err.println(fileName + "--文件上传失败");
            }
            String url =http + flag + "-" + fileName;
            // 编辑器要求的返回格式
            if ("img".equals(type)) {  // 上传图片
                return Dict.create().set("errno", 0).set("data", CollUtil.newArrayList(Dict.create().set("url", url)));
            } else if ("video".equals(type)) {
                return Dict.create().set("errno", 0).set("data", Dict.create().set("url", url));
            }
            return Dict.create().set("errno", 0);
        }
    }

    /**
     * 获取文件
     */
    @ApiOperation(value = "获取文件")
    @GetMapping("/{flag}")
    public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
        if (!FileUtil.isDirectory(filePath)) {
            FileUtil.mkdir(filePath);
        }
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(filePath);
        String avatar = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(avatar)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + avatar);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }

}
