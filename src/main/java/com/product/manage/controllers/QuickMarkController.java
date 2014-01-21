package com.product.manage.controllers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.util.StringUtils;
import com.product.manage.conf.UploadConf;
import com.product.manage.models.App;
import com.product.manage.models.Pakkage;
import com.product.manage.services.AppService;
import com.product.manage.services.PackageService;
import com.swetake.util.Qrcode;

@Controller("mark")
@RequestMapping("/mark")
public class QuickMarkController {
    
    @Autowired
    AppService appService;
    @Autowired
    PackageService packageService;
    
    private int size = 4;

    /**
     * @param request
     * @param response
     * @param id 应用包id
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void getQuickMark(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "id", required = true) int id) {
        try {
            Pakkage pakkage = packageService.getPackageById(id);
            if(pakkage == null) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("安装包不存在");
                return;
            }
            App app = appService.getOneAppById(pakkage.getApp_id());
            StringBuffer url = request.getRequestURL();
            String content = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString() + "product-manage/download/get.do?id=" + id;
            Qrcode qrcodeHandler = new Qrcode();
            qrcodeHandler.setQrcodeErrorCorrect('M');
            qrcodeHandler.setQrcodeEncodeMode('B');
            qrcodeHandler.setQrcodeVersion(size);
  
            // System.out.println(content);
            byte[] contentBytes = content.getBytes("gb2312");
            //构造一个BufferedImage对象 设置宽、高
            int imgSize = 67 + 12 * (size - 1);
            BufferedImage bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = bufImg.createGraphics();
  
            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, imgSize, imgSize);
  
            // 设定图像颜色 > BLACK  
            gs.setColor(Color.BLACK);
  
            // 设置偏移量 不设置可能导致解析出错  
            int pixoff = 2;
            // 输出内容 > 二维码  
            if (contentBytes.length > 0 && contentBytes.length < 120) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }  
                    }  
                }  
            } else {  
                System.err.println("QRCode content bytes length = "  
                        + contentBytes.length + " not in [ 0,120 ]. ");
            }
            if(!StringUtils.isEmpty(app.getIcon())) {
                @SuppressWarnings("deprecation")
                String path = request.getRealPath(UploadConf.imageFold) + System.getProperty("file.separator") + app.getIcon();
                Image img = ImageIO.read(new File(path));//实例化一个Image对象。
                int x = (imgSize - 20) / 2;
                gs.drawImage(img, x, x, 20, 20, null);
            }
            gs.dispose();
            bufImg.flush();
            // 生成二维码QRCode图片  
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            ServletOutputStream sos;
            try {
                sos = response.getOutputStream();
                ImageIO.write(bufImg, "jpeg", sos);
                sos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
  
        }catch (Exception e){  
            e.printStackTrace();
        }  
        
    }
}
