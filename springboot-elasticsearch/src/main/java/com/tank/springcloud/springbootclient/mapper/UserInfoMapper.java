package com.tank.springcloud.springbootclient.mapper;

import com.tank.springcloud.springbootclient.entity.UserInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * ElasticsearchTemplate、ElasticsearchRepository、ElasticsearchRestTemplate区别
 * ElasticsearchRepository可以做Elasticsearch的相关数据的增删改查，用法和普通的接口是一样的，这样就能统一ElasticSearch和普通的JPA操作，获得和操作mysql一样的代码体验。同时也可以看到ElasticsearchRepository的功能是比较少的，简单查询够用，但复杂查询就稍微显得力不从心了。
 * ElasticsearchRepository继承自PagingAndSortingRepository，PagingAndSortingRepository又继承CrudRepository
 * ElasticsearchRepository中的方法基本都过时了
 */
public interface UserInfoMapper extends ElasticsearchRepository<UserInfo,String> {
    List<UserInfo> findByName(String name);

    //List<UserInfo> findNameByLike(String name);
}
