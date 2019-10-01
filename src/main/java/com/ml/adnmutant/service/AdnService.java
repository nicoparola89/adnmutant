package com.ml.adnmutant.service;

import com.ml.adnmutant.domain.Adn;

public interface AdnService {

    /**
     * Save a adn.
     *
     * @param Adn the entity to save
     * @return the persisted entity
     */
    Adn save(Adn adn);

    Iterable<Adn> findAll();

}
