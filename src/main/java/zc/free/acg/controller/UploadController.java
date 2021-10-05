package zc.free.acg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import zc.free.acg.service.UploadService;

/**
 * @auther ZhengCong
 * @date 2020/3/31 14:57
 * 上传文件
 */
@Api(description = "上传文件接口")
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 上传图片文件 返回文件的路径
     * @param file
     * @return
     */
    @ApiOperation(value = "上传图片到服务器 返回图片地址", notes="")
    @ApiImplicitParam(name = "file", value = "上传的图片",required = true, dataType = "MultipartFile")
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String url = this.uploadService.uploadImage(file);
        if(StringUtils.isBlank(url)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }

    @ApiOperation(value = "上传文件到服务器 返回文件地址", notes="")
    @ApiImplicitParam(name = "file", value = "上传文件",required = true, dataType = "MultipartFile")
    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String url = this.uploadService.uploadFile(file);
        if(StringUtils.isBlank(url)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }

}
