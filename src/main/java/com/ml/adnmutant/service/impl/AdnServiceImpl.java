package com.ml.adnmutant.service.impl;

import com.ml.adnmutant.domain.Adn;
import com.ml.adnmutant.repository.AdnRepository;
import com.ml.adnmutant.repository.AdnRepositoryCustom;
import com.ml.adnmutant.service.AdnService;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdnServiceImpl implements AdnService {

    private final AdnRepository adnRepository;


    public AdnServiceImpl(AdnRepository adnRepository) {
        this.adnRepository = adnRepository;

    }

    @Override
    public Adn save(Adn adn){
        return adnRepository.save(adn);
    }

    public Iterable<Adn> findAll() {
        return adnRepository.findAll();
    }

    public Adn findById(String id){
        Optional<Adn> adn = adnRepository.findById(id);
        if(adn.isPresent()){
            return adn.get();
        }else{
            return null;
        }
    }



}
