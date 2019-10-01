package com.ml.adnmutant.repository;

import com.ml.adnmutant.service.dto.Stats;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdnRepositoryCustomImpl implements AdnRepositoryCustom {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public Stats getStats(){

        SearchRequestBuilder searchRequestBuilder =  elasticsearchTemplate.getClient().prepareSearch("register")
                .setTypes("adn").addAggregation(AggregationBuilders.terms("countMutant").field("mutant"));
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
        LongTerms stringTerms = searchResponse.getAggregations().get("countMutant");
        List<LongTerms.Bucket> bucketList = stringTerms.getBuckets();
        Stats stats = new Stats();
        for(LongTerms.Bucket bucket :bucketList){

            Long key = (Long)bucket.getKey();
            if(key == 1){
                stats.setCountMutantDna(bucket.getDocCount());
            }else {
                stats.setCountHumanDna(bucket.getDocCount());
            }

        }

        return stats;

    }

}
