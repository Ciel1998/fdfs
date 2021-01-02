package com.liziyi.fdfs.controller;

import com.liziyi.fdfs.FastDFSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @Description
 * @Author liziyi
 * @CreateDate 2020/12/29 16:34
 * @UpdateUser
 * @UpdateDate
 * @UpdateRemark
 */
@EnableSwagger2
@RestController
@RequestMapping("/request")
@Api(description = "fdfs文件上传与下载")
public class UploadController {
    private static final  String URL = "classpath:fdfs_client.conf";

    @PostMapping("/uploadFile")
    @ApiOperation("fdfs方法上传")
    public String uploadFile(@RequestParam("file") MultipartFile uploadFile) throws Exception {
        //文件名
        String originalName = uploadFile.getOriginalFilename();
        //拓展名
        String name = originalName.substring(originalName.lastIndexOf('.')+1);
        String path = FastDFSUtils.upload_file(uploadFile.getBytes(), originalName, uploadFile.getSize());
        return path;
    }

    //没实现选定下载地址
    @GetMapping("/downLoadFile")
    @ApiOperation("fdfs方法下载")
    public String downLoadFile() throws Exception {
        byte[] bytes = FastDFSUtils.download_file("group1", "M00/00/00/wKjngl_wTvWAIDq-AASE-wcdb3Y241.jpg");
        // 1.用日期命名文件
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = dateFormat.format(now) + ".jpg";
        System.out.println(fileName);
        // 2.转为图片jpg格式
        //()中是下载到的地址
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\LZY\\Downloads\\"+fileName);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        return "下载成功";
    }


    @GetMapping("/deleteFile")
    @ApiOperation("fdfs方法删除")
    public String deleteFile() throws Exception {
        FastDFSUtils.delete_file("group1", "M00/00/00/wKjngl_u13iAGTRcAACMjz-Fy6g372.jpg");
        return "删除成功";
    }
}
