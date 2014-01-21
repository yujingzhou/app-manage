package com.product.manage.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: App
 * @Description: 应用实体
 * @author: 于勃
 * @date: 2014年1月14日 下午5:41:25
 */
public class App {
    
    private int id;
    private String appname;
    private String description;
    private String icon;
    private Date update_time;
    
    public String getUpdateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(update_time);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getAppname() {
        return appname;
    }
    
    public void setAppname(String appname) {
        this.appname = appname;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public Date getUpdate_time() {
        return update_time;
    }
    
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    
}
