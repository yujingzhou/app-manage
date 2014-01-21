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

import com.product.manage.conf.PackageConf;
import com.product.manage.conf.UploadConf;
import com.product.manage.models.App;
import com.product.manage.models.Pakkage;
import com.product.manage.services.AppService;
import com.product.manage.services.PackageService;
import com.product.manage.utils.UploadUtil;

@Controller("package")
@RequestMapping("/package")
public class PackageController {
    
    @Autowired
    AppService appService;
    
    @Autowired
    PackageService packageService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "appid", required = true) int appid) {
        model.addAttribute("app", appService.getOneAppById(appid));
        model.addAttribute("packages", packageService.listAllByAppId(appid));
        model.addAttribute("types", PackageConf.typePackageMap);
        return "package_list";
    }
    
    /**
     * @param model
     * @param request
     * @param response
     * @param appid 应用ID
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getAddPackage(ModelMap model, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "appid", required = true) int appid) {
        model.addAttribute("appid", appid);
        model.addAttribute("appTypes", PackageConf.typeMap);
        return "package_get";
        
    }
    
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public String addPackage(ModelMap model, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "appid", required = true) int appid,
            @RequestParam(value = "type", required = true) int type,
            @RequestParam(value = "version", required = false) String version,
            @RequestParam(value = "pfile", required = true) MultipartFile file) {
        Map<String, String> errors = new HashMap<String, String>();
        model.addAttribute("errors", errors);
        App app = appService.getOneAppById(appid);
        if(app == null) {
            errors.put("type", "应用不存在");
            return "package_get";
        }
        if(!typeIsValidate(type)) {
            errors.put("type", "应用类型不合法");
            return "package_get";
        }
        file.getContentType();
        String saveFileName = "";
        String fileType = "";
        if(file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            if(!fileTypeIsValidate(type, fileType)) {
                errors.put("pfile", "应用包与应用类型不一致");
                return "package_get";
            }
            saveFileName = String.valueOf(System.currentTimeMillis()) + "." + fileType;
            if(!UploadUtil.saveFile(request.getRealPath(UploadConf.packageFold) + System.getProperty("file.separator") + saveFileName, file)) {
                errors.put("pfile", "应用上传失败");
                return "package_get";
            }
        }
        Pakkage pakkage = new Pakkage();
        pakkage.setApp_id(appid);
        pakkage.setPath(saveFileName);
        pakkage.setType(type);
        pakkage.setVersion(version);
        packageService.addPackage(pakkage);
        return "redirect:/package/list.do?appid=" + appid;
    }
    
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showPackage(ModelMap model, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "id", required = true) int id) {
        model.addAttribute("appTypes", PackageConf.typeMap);
        model.addAttribute("package", packageService.getPackageById(id));
        return "package_show";
    }
    
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePackage(ModelMap model, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "type", required = true) int type,
            @RequestParam(value = "version", required = false) String version,
            @RequestParam(value = "pfile", required = false) MultipartFile file) {
        Map<String, String> errors = new HashMap<String, String>();
        model.addAttribute("errors", errors);
        if(!typeIsValidate(type)) {
            errors.put("type", "应用类型不合法");
            return "package_get";
        }
        Pakkage pakkage = packageService.getPackageById(id);
        if(pakkage == null) {
            errors.put("type", "安装包不存在");
            return "package_get";
        }
        file.getContentType();
        String saveFileName = "";
        String fileType = "";
        if(file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            if(!fileTypeIsValidate(type, fileType)) {
                errors.put("pfile", "应用包与应用类型不一致");
                return "package_get";
            }
            saveFileName = String.valueOf(System.currentTimeMillis()) + "." + fileType;
            if(!UploadUtil.saveFile(request.getRealPath(UploadConf.packageFold) + System.getProperty("file.separator") + saveFileName, file)) {
                errors.put("pfile", "应用上传失败");
                return "package_get";
            }
            pakkage.setPath(saveFileName);
        }
        pakkage.setType(type);
        pakkage.setVersion(version);
        packageService.updatePackage(pakkage);
        return "redirect:/package/list.do?appid=" + pakkage.getApp_id();
    }
    
    @RequestMapping(value = "/destroy", method = RequestMethod.GET)
    public String deletePackage(ModelMap model, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "appid", required = true) int appid,
            @RequestParam(value = "id", required = true) int id) {
        App app = appService.getOneAppById(appid);
        if(app == null) {
            return "redirect:/app/list.do";
        }
        packageService.deletePackage(id);
        return "redirect:/package/list.do?appid=" + appid;
    }
    
    private boolean typeIsValidate(int type) {
        return PackageConf.typeIntMap.containsKey(type);
    }
    
    private boolean fileTypeIsValidate(int type, String fileType) {
        return PackageConf.typeIntMap.get(type).equals(fileType);
    }
}
