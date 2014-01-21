package com.product.manage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.manage.mappers.PackageMapper;
import com.product.manage.models.Pakkage;

@Service("pakkage")
public class PackageService {
    
    @Autowired
    PackageMapper packageMapper;
    
    public int addPackage(Pakkage pakkage) {
        return packageMapper.add(pakkage);
    }
    
    public int deletePackage(int id) {
        return packageMapper.delete(id);
    }
    
    public Pakkage getPackageById(int id) {
        return packageMapper.queryOne(id);
    }
    
    public List<Pakkage> listAllByAppId(int appid) {
        return packageMapper.list(appid);
    }
    
    public int updatePackage(Pakkage pakkage) {
        return packageMapper.update(pakkage);
        
    }
}
