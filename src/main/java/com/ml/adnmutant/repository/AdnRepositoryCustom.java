package com.ml.adnmutant.repository;

import com.ml.adnmutant.service.dto.Stats;
import org.elasticsearch.action.search.SearchResponse;

public interface AdnRepositoryCustom {

    Stats getStats();
}
