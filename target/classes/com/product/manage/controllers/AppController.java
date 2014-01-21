package com.product.manage.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.product.manage.conf.UploadConf;
import com.product.manage.models.App;
import com.product.manage.services.AppService;
import com.product.manage.utils.UploadUtil;

@Controller("app")
@RequestMapping("/app")
public class AppController {

    @Autowired
    AppService appService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getAddPage(ModelMap model, HttpServletRequest request,
            HttpServletResponse response) {
        return "app_get";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, HttpServletRequest request,
            HttpServletResponse response) {
        model.addAttribute("apps", appService.lisAllApp());
        return "app_list";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public String addApp(ModelMap model, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "appname", required = true) String appName,
            @RequestParam(value = "description", required = true) String desc,
            @RequestParam(value = "icon", required = false) MultipartFile file) {
        Map<String, String> errors = new HashMap<String, String>();
        model.addAttribute("errors", errors);
        file.getContentType();
        String saveFileName = "";
        String fileType = "";
        if(file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            if(!UploadConf.imageTypes.contains(fileType)) {
                errors.put("icon", "应用图标类型不合法");
                return "app_get";
            }
            saveFileName = String.valueOf(System.currentTimeMillis()) + "." + fileType;
            if(!UploadUtil.saveFile(request.getRealPath(UploadConf.imageFold) + System.getProperty("file.separator") + saveFileName, file)) {
                errors.put("icon", "应用图标上传失败");
                return "app_get";
            }
        }
        App app = new App();
        app.setAppname(appName);
        app.setDescription(desc);
        app.setIcon(saveFileName);
        appService.addOneApp(app);
        return "redirect:/app/list.do";
    }
    
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap model, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "appname", required = true) String appName,
            @RequestParam(value = "description", required = true) String desc,
            @RequestParam(value = "icon", required = false) MultipartFile file) {
        Map<String, String> errors = new HashMap<String, String>();
        model.addAttribute("errors", errors);
        file.getContentType();
        String saveFileName = "";
        String fileType = "";
        if(file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            if(!UploadConf.imageTypes.contains(fileType)) {
                errors.put("icon", "应用图标类型不合法");
                return "app_show";
            }
            saveFileName = String.valueOf(System.currentTimeMillis()) + "." + fileType;
            if(!UploadUtil.saveFile(request.getRealPath(UploadConf.imageFold) + System.getProperty("file.separator") + saveFileName, file)) {
                errors.put("icon", "应用图标上传失败");
                return "app_show";
            }
        }
        App app = new App();
        app.setId(id);
        app.setAppname(appName);
        app.setDescription(desc);
        app.setIcon(saveFileName);
        return "redirect:/app/list.do";
    }
    
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showApp(ModelMap model, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "id", required = true) int id) {
        model.addAttribute("app", appService.getOneAppById(id));
        return "app_show";
    }
    
    @RequestMapping(value = "/destroy", method = RequestMethod.GET)
    public String deleteApp(ModelMap model, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "id", required = true) int id) {
        appService.delete(id);
        return "redirect:/app/list.do";
    }
}
