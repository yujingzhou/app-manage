package com.product.manage.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.manage.conf.PackageConf;
import com.product.manage.conf.UploadConf;
import com.product.manage.models.App;
import com.product.manage.models.Pakkage;
import com.product.manage.services.AppService;
import com.product.manage.services.PackageService;

@Controller("download")
@RequestMapping("/download")
public class DownloadController {
    
    @Autowired
    PackageService packageService;
    
    @Autowired
    AppService appService;
    
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "id", required = true) int id) throws IOException {
        Pakkage pakkage = packageService.getPackageById(id);
        if(pakkage == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("安装包不存在");
            return;
        }
        App app = appService.getOneAppById(pakkage.getApp_id());
        @SuppressWarnings("deprecation")
        String path = request.getRealPath(UploadConf.packageFold) + System.getProperty("file.separator") + pakkage.getPath();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("安装包不存在");
                return;
            }
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream(file));
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            response.setHeader("Content-disposition", "attachment;filename="
                    + app.getAppname() + "." + PackageConf.typeIntMap.get(pakkage.getType()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response
              .getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
