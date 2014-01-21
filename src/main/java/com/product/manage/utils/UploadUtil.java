package com.product.manage.utils;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: UploadUtil
 * @Description: 上传帮助类
 * @author: 于勃
 * @date: 2014年1月16日 上午10:29:30
 */
public class UploadUtil {
    
    public static boolean saveFile(String fileName, MultipartFile file) {
        boolean flag = false;
        try {
//            file.transferTo(dest);
            InputStream stream = file.getInputStream();
            FileOutputStream fs = new FileOutputStream(fileName);
            byte[] buffer = new byte[4 * 1024 * 1024];
            int byteread = 0;
            while ((byteread = stream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
                fs.flush();
            }
            fs.close();
            stream.close();
            flag = true;
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

}
