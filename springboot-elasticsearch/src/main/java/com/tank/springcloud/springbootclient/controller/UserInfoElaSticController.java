package com.tank.springcloud.springbootclient.controller;

import com.alibaba.fastjson.JSON;
import com.tank.springcloud.springbootclient.entity.UserInfo;
import com.tank.springcloud.springbootclient.mapper.UserInfoMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class UserInfoElaSticController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @RequestMapping(value = "/test/getElaById")
    public String getUserInfo() {
        UserInfo userInfo = userInfoMapper.findById("85DL2YYBw94paJn_bUH4").get();
        List<UserInfo> alibabaList = userInfoMapper.findByName("alibaba");
        //return JSON.toJSONString(alibabaList);
        return JSON.toJSONString(userInfo);
    }

    @RequestMapping(value = "/test/add")
    public String saveElastic() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(30);
        userInfo.setCreatBy("autoCreate");
        userInfo.setCreateDt(System.currentTimeMillis());
        userInfo.setName("Elasticsearch重启");
        userInfo.setStatsh("为何每次重启,处理很长时间");
        userInfo.setTitle("elasticsearch docker重启分析");
        userInfoMapper.save(userInfo);

        Iterable<UserInfo> all = userInfoMapper.findAll();

        return "保存成功" + "---" + JSON.toJSONString(all);
    }

    @RequestMapping(value = "/test/deleteById")
    public String deleteById() {
        UserInfo userInfo = userInfoMapper.findById("So3U2YYBev_pHIBU58v3").get();
        if (Objects.nonNull(userInfo)) {
            userInfoMapper.delete(userInfo);
        }
        Iterable<UserInfo> all = userInfoMapper.findAll();

        return "删除成功" + "---" + JSON.toJSONString(all);
    }

    @RequestMapping("/test/page")
    public String page() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //构建by参数查询
        builder.withQuery(QueryBuilders.termQuery("name", "alibaba"));
        int pageSize = 10;
        int currentPage = 0;
        builder.withPageable(PageRequest.of(currentPage, pageSize));
        Page<UserInfo> search = userInfoMapper.search(builder.build());

        return JSON.toJSONString("记录数：" + search.getTotalElements() + "--" + search.getContent());

    }

    /**
     * elasticsearchRestTemplate 查询用法
     *
     * @return
     */
    @RequestMapping("/test/template")
    public String findByTitle() {
        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("name", "重启")).build();
        SearchHits<UserInfo> search = elasticsearchRestTemplate.search(build, UserInfo.class);
        List<SearchHit<UserInfo>> searchHits = search.getSearchHits();
        return JSON.toJSONString(searchHits);
    }


    /**
     * 文本高亮查询显示
     * @return
     */
    @RequestMapping("/test/templateByHits")
    public String templateByHits() {
        //需要查询的字段
        BoolQueryBuilder should = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", "动创"))
                .should(QueryBuilders.matchQuery("name", "建标"));
        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(should)
                .withHighlightFields(
                        new HighlightBuilder.Field("title")
                        , new HighlightBuilder.Field("name"))
                // 注意preTags和postTags区别
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        //查询结果
        SearchHits<UserInfo> search = elasticsearchRestTemplate.search(searchQuery, UserInfo.class);
        List<SearchHit<UserInfo>> searchHits = search.getSearchHits();
        List<UserInfo> list = new ArrayList<>();
        for (SearchHit<UserInfo> userInfo : searchHits) {
            Map<String, List<String>> highlightFields = userInfo.getHighlightFields();
            userInfo.getContent().setName(highlightFields.get("name") == null ? userInfo.getContent().getName() : highlightFields.get("name").get(0));
            userInfo.getContent().setTitle(highlightFields.get("title") == null ? userInfo.getContent().getTitle() : highlightFields.get("title").get(0));
            list.add(userInfo.getContent());
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping("/test/templateByHits2")
    public String getDataByHit() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", "重启"))
                .should(QueryBuilders.matchQuery("name", "重启"));
        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightFields(
                        new HighlightBuilder.Field("title")
                        , new HighlightBuilder.Field("name"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        //查询
        SearchHits<UserInfo> search = elasticsearchRestTemplate.search(searchQuery, UserInfo.class);
        //得到查询返回的内容
        List<SearchHit<UserInfo>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<UserInfo> users = new ArrayList<>();
        //遍历返回的内容进行处理
        for (SearchHit<UserInfo> searchHit : searchHits) {
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setName(highlightFields.get("name") == null ? searchHit.getContent().getName() : highlightFields.get("name").get(0));
            searchHit.getContent().setTitle(highlightFields.get("title") == null ? searchHit.getContent().getTitle() : highlightFields.get("title").get(0));
            //放到实体类中
            users.add(searchHit.getContent());
        }
        return JSON.toJSONString(users);
    }

    /**
     * 分页查询UserInfo
     */
    @RequestMapping("/test/pageByHits")
    public String pageByHits() {
        //需要查询的字段
        BoolQueryBuilder should = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", "重启"))
                .should(QueryBuilders.matchQuery("name", "重启"));
        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(should)
                .withHighlightFields(
                        new HighlightBuilder.Field("title")
                        , new HighlightBuilder.Field("name"))
                // 注意preTags和postTags区别
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        //查询结果
        SearchHits<UserInfo> search = elasticsearchRestTemplate.search(searchQuery, UserInfo.class);
        List<SearchHit<UserInfo>> searchHits = search.getSearchHits();
        List<UserInfo> list = new ArrayList<>();
        for (SearchHit<UserInfo> userInfo : searchHits) {
            Map<String, List<String>> highlightFields = userInfo.getHighlightFields();
            userInfo.getContent().setName(highlightFields.get("name") == null ? userInfo.getContent().getName() : highlightFields.get("name").get(0));
            userInfo.getContent().setTitle(highlightFields.get("title") == null ? userInfo.getContent().getTitle() : highlightFields.get("title").get(0));
            list.add(userInfo.getContent());
        }
        return JSON.toJSONString(list);
    }

    /**
     * 更新ElasticSearch com.tank.springcloud.springbootclient.entity.UserInfo by Id索引对象
     */
    @RequestMapping("/test/updateById")
    public String updateById() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("更新名称");
        userInfo.setTitle("更新标题");
        userInfo.setStatsh("更新统计");
        userInfo.setAge(20);
        //更新
        elasticsearchRestTemplate.save(userInfo);
        return "更新成功";
    }


}
