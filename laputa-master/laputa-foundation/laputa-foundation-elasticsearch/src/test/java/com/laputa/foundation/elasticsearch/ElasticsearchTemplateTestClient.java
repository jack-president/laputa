package com.laputa.foundation.elasticsearch;

import com.laputa.foundation.elasticsearch.configuration.LaputaElasticsearchConfiguration;
import com.laputa.foundation.elasticsearch.repositories.City;
import com.laputa.foundation.elasticsearch.repositories.CityElasticsearchDbRepository;
import com.laputa.foundation.elasticsearch.repositories.CityIndex;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;

/**
 * Created by jiangdongping on 2018/3/6 0006.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ElasticsearchTemplateTestClient.class})
@Import(value = {LaputaElasticsearchConfiguration.class})
@ImportResource(value = {"classpath*:/laputa-config/springcontext/applicationcontext-foundation-*.xml"})
public class ElasticsearchTemplateTestClient {

    private Logger logger = LoggerFactory.getLogger(ElasticsearchTemplateTestClient.class);

    @Autowired(required = false)
    private CityElasticsearchDbRepository s;

    @Autowired(required = false)
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired(required = false)
    private CityIndex cityIndex;


    @Bean
    public CityIndex cityIndex() {
        CityIndex c = new CityIndex();
        c.setIndex("ssssss");
        return c;
    }

    @Test
    public void tttttttttt(){
        logger.error("sssssssssss");
    }

    @Test
    public void elasticsearchTemplateTest() throws InterruptedException {
        City city = new City("qqqqqqqq", "wwwwwwwwwww");
        city.setLogdate("2012-03-14");
        //s.save(city);


        s.save(city);

        cityIndex.setIndex("ssssssssssssss");

        s.save(city);
        s.findAll();

        IndexQuery indexQuery = new IndexQueryBuilder().withIndexName("c-0314").withObject(city).build();

        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withIndexName("cc-2012-03-14").withClass(City.class).withType("s-log").build();

        elasticsearchTemplate.index(indexQuery);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withIndices("city-2222", "city-2222cc-6688");

        Object o = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), City.class);
        o.getClass();

        Thread.sleep(88888L);

    }


    @Autowired
    private TransportClient transportClient;

    @Test
    public void testNative() throws ExecutionException, InterruptedException {


    }
}
