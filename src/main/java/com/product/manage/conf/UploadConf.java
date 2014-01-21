package com.product.manage.conf;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UploadConf
 * @Description: 上传配置
 * @author: 于勃
 * @date: 2014年1月15日 下午6:19:32
 */
public class UploadConf {
    
    public static List<String> imageTypes = new ArrayList<String>();
    public static String iosAppType = "ipa";
    public static String androidAppType = "apk";
    public static String imageFold = "/images";
    public static String packageFold = "/packages";
    
    static {
        initImageTypes();
    }
    
    private static void initImageTypes(){
        imageTypes.add("jpg");
        imageTypes.add("jpeg");
        imageTypes.add("bmp");
        imageTypes.add("png");
    }
    
}
