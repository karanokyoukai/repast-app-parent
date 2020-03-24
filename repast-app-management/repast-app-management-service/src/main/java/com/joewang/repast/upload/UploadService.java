/**
 * @Company AAA软件教育
 * @Author Zero
 * @Date Create in 2020/3/18 15:01
 * @Description
 * 通过ftp进行上传和下载
 **/
package com.joewang.repast.upload;

import com.joewang.repast.properties.FtpProperties;
import com.joewang.repast.utils.DateUtil;
import com.joewang.repast.utils.FileNameUtil;
import com.joewang.repast.utils.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static com.joewang.repast.staticstatus.RequestProperties.POINT;
import static com.joewang.repast.staticstatus.StaticCode.FORMAT_DATE;

@Service
public class UploadService {
    @Autowired
    private FtpProperties ftpProperties;

    public Boolean upload(MultipartFile file,String token){
        // 目前来说有两个参数不舒服:1.filePath，2.fileName
        // 防止文件名重复，导致文件覆盖
        // 1.文件名替换
        String oldFileName = file.getOriginalFilename();
        String newFileName = FileNameUtil.getFileName(token);
        newFileName = newFileName + oldFileName.substring(oldFileName.lastIndexOf(POINT));
        // 2.filePath
        String filePath = DateUtil.formatDate(new Date(), FORMAT_DATE);
        try {
            return FtpUtil.uploadFile(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername()
                    , ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}