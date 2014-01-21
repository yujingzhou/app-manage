package com.product.manage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: HomeController
 * @Description: 工程根路径
 * @author: 于勃
 * @date: 2014年1月16日 下午3:43:09
 */
@Controller
@RequestMapping("")
public class HomeController {
    
    @RequestMapping("home")
    public String home() {
        return "home";
    }
}
