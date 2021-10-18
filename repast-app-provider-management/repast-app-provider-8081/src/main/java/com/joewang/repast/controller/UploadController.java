/**
 * @Company SS.Ed
 * @Author Zero
 * @Date Create in 2020/3/18 15:08
 * @Description
 **/
package com.joewang.repast.controller;

import com.joewang.repast.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /*
     * @author Zero
     * @description ftp文件上传
     * @param  [file, token]
     * @date 2020/3/18 15:12
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean uploadFile(@RequestBody MultipartFile file, @RequestParam("TOKEN")String token){
        return uploadService.upload(file,token);
    }
}