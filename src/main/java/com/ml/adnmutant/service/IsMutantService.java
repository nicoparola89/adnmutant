package com.ml.adnmutant.service;

import com.ml.adnmutant.domain.Adn;
import com.ml.adnmutant.service.dto.Stats;

public interface IsMutantService {
    /**
     * check is mutant ADN.
     *
     * @param Char[][] ADN
     * @return boolean is mutant and
     */
    boolean isMutant(String[] adn);

    Stats getStats();



}
