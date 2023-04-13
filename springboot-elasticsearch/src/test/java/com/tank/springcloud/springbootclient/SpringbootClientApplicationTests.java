package com.tank.springcloud.springbootclient;

import com.tank.springcloud.springbootclient.entity.GoodsEntity;
import com.tank.springcloud.springbootclient.util.JacksonUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootElasticSearcApplication.class)
public class SpringbootClientApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     */
    @Test
    public void createIndex() {
        CreateIndexRequest request = new CreateIndexRequest("test_index");
        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(createIndexResponse.index());
        // Assert.assertEquals(createIndexResponse.index(),"");
    }

    @Test
    public void createDocument() throws IOException {
        GoodsEntity goods = new GoodsEntity();
        goods.setGoodsDesc("aiming");
        goods.setGoodsId(20000L + "");
        goods.setGoodsName("无价之宝...");
        IndexRequest request = new IndexRequest("goods_index");
        //request.id("3");//设置规则
        request.timeout(TimeValue.timeValueSeconds(10));//设置超时时间
        request.source(JacksonUtils.toJsonString(goods), XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println("respone" + response.toString());
    }

    @Test
    public void getDocument() throws IOException {
        GetRequest request = new GetRequest("goods_index","1");

        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println("文档是否存在："+exists);
        //如果存在，获取文档信息
        if (exists){
            GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            System.out.println("文档内容为："+response.getSourceAsString());
        }
        System.out.println("-----");
    }


}
