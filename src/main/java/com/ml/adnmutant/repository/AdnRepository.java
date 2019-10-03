package com.ml.adnmutant.repository;

import com.ml.adnmutant.domain.Adn;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdnRepository extends ElasticsearchRepository<Adn,String> {

}
