package com.laputa.logger.web.service;

import com.laputa.foundation.elasticsearch.configuration.LaputaElasticsearchConfiguration;
import com.laputa.logger.web.LoggerManagerConfiguration;
import com.laputa.logger.web.elasticsearch.repositories.type.Log;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by JiangDongPing on 2018/04/08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LoggerElasticsearchServiceTestClient.class})
@Import(value = {LaputaElasticsearchConfiguration.class, LoggerManagerConfiguration.class})
@ImportResource(value = {"classpath*:/laputa-config/springcontext/applicationcontext-foundation-*.xml"})
public class LoggerElasticsearchServiceTestClient {

    @Autowired(required = false)
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testDemo() {

        String preTag = "<font color=‘#dd4b39‘>";//google的色值
        String postTag = "</font>";
//"message", "DefaultDocumentLoader "

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //nativeSearchQueryBuilder.withPageable(Pageable.unpaged());
        nativeSearchQueryBuilder.withIndices("logstash-2018.04.09");
        //nativeSearchQueryBuilder.withTypes("doc");

//        MoreLikeThisQueryBuilder moreLikeThisQueryBuilder = QueryBuilders.moreLikeThisQuery(
//                new String[]{"ZooKeeper"},
//
//                new MoreLikeThisQueryBuilder.Item[]{new MoreLikeThisQueryBuilder.Item().fields("message")});

        //nativeSearchQueryBuilder.withQuery(new BoolQueryBuilder().must(QueryBuilders.fuzzyQuery("message", "d")));

        //nativeSearchQueryBuilder.withQuery(new BoolQueryBuilder().must(QueryBuilders.termQuery("transId", "e40a03a7e0514c0b9925017b5af12e25")));
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("@timestamp").order(SortOrder.DESC));


        //nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("message").preTags(preTag).postTags(postTag));


        List<Log> list = elasticsearchTemplate.queryForList(nativeSearchQueryBuilder.build(), Log.class);

    }
}
