package com.product.manage.conf;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: PackageConf
 * @Description: 
 * @author: 于勃
 * @date: 2014年1月17日 下午3:01:29
 */
public class PackageConf {
    
    public static Map<String, Integer> typeMap = new HashMap<String, Integer>();
    
    public static Map<Integer, String> typeIntMap = new HashMap<Integer, String>();
    
    public static Map<Integer, String> typePackageMap = new HashMap<Integer, String>();
    
    static{
        initTypeMap();
        initTypeIntMap();
        initTypePackageMap();
    }
    
    private static void initTypeMap() {
        typeMap.put("ios", 1);
        typeMap.put("android", 2);
    }
    
    private static void initTypeIntMap() {
        typeIntMap.put(1, "ipa");
        typeIntMap.put(2, "apk");
    }
    
    private static void initTypePackageMap() {
        typePackageMap.put(1, "ios");
        typePackageMap.put(2, "android");
    }

}
