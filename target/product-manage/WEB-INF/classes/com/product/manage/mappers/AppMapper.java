package com.product.manage.mappers;

import java.util.List;

import com.product.manage.annotations.MyBatisRepository;
import com.product.manage.models.App;

/**
 * @ClassName: ProductMapper
 * @Description: app的数据库访问dao
 * @author: 于勃
 * @date: 2013年12月2日 上午10:54:39
 */
@MyBatisRepository
public interface AppMapper {

    int add(App app);
    
    List<App> list();
    
    int delete(int id);
    
    int update(App app);
    
    App queryOne(int id);

}
