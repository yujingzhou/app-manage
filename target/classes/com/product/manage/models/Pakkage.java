package com.product.manage.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: Package
 * @Description: 应用包实体
 * @author: 于勃
 * @date: 2014年1月14日 下午5:44:43
 */
public class Pakkage {
    
    private int id;
    private int type;
    private String version;
    private String path;
    private Date update_time;
    private int app_id;

    public String getUpdateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(update_time);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public Date getUpdate_time() {
        return update_time;
    }
    
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    
    public int getApp_id() {
        return app_id;
    }
    
    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }
    
}
