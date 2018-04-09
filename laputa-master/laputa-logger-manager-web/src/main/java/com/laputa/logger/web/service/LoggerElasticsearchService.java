package com.laputa.logger.web.service;

import com.laputa.logger.web.elasticsearch.repositories.type.Log;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by JiangDongPing on 2018/04/08.
 */
@Service("loggerElasticsearchService")
public class LoggerElasticsearchService {

    @Autowired(required = true)
    private ElasticsearchTemplate elasticsearchTemplate;


    public List<Log> query(String transId) {
        String preTag = "<font color=‘#dd4b39‘>";//google的色值
        String postTag = "</font>";

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //nativeSearchQueryBuilder.withPageable(Pageable.unpaged());
        nativeSearchQueryBuilder.withIndices("logstash-2018.04.09");
        //nativeSearchQueryBuilder.withTypes("doc");

//        MoreLikeThisQueryBuilder moreLikeThisQueryBuilder = QueryBuilders.moreLikeThisQuery(
//                new String[]{"ZooKeeper"},
//
//                new MoreLikeThisQueryBuilder.Item[]{new MoreLikeThisQueryBuilder.Item().fields("message")});

//        nativeSearchQueryBuilder.withQuery(new BoolQueryBuilder().must(QueryBuilders.fuzzyQuery("message", "d")));

        if (StringUtils.hasLength(transId)) {
            nativeSearchQueryBuilder.withQuery(new BoolQueryBuilder().must(QueryBuilders.termQuery("transId", transId)));
        }
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("@timestamp").order(SortOrder.DESC));


        //nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("message").preTags(preTag).postTags(postTag));


        List<Log> list = elasticsearchTemplate.queryForList(nativeSearchQueryBuilder.build(), Log.class);

        return list;
    }


}
