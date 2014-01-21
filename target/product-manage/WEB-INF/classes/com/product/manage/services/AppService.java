package com.product.manage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.manage.mappers.AppMapper;
import com.product.manage.models.App;

/**
 * @ClassName: AppService
 * @Description: 应用服务类
 * @author: 于勃
 * @date: 2014年1月15日 上午10:28:02
 */
@Service("AppService")
public class AppService {
    
    @Autowired
    AppMapper appMapper;
    
    public int addOneApp(App app) {
        //检查app合理性
        return appMapper.add(app);
    }
    
    public int delete(int id) {
        return appMapper.delete(id);
    }
    
    public App getOneAppById(int id) {
       return appMapper.queryOne(id);
    }
    
    public int updateAppById(App app) {
        return appMapper.update(app);
    }
    
    public List<App> lisAllApp() {
        return appMapper.list();
    }

}
