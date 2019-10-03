package com.ml.adnmutant.service.impl;

import com.ml.adnmutant.domain.Adn;
import com.ml.adnmutant.repository.AdnRepositoryCustom;
import com.ml.adnmutant.service.AdnService;
import com.ml.adnmutant.service.IsMutantService;
import com.ml.adnmutant.service.dto.Stats;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class IsMutantServiceImpl implements IsMutantService {

    private final AdnService adnService;

    @Autowired
    private AdnRepositoryCustom adnRepositoryCustom;

    public IsMutantServiceImpl(AdnService adnService ) {
        this.adnService = adnService;
    }

    private int secuencesFound = 0;

    @Override
    public boolean isMutant(String[] dna) {
        if(dna.length < 4){
            return false;
        }
        char[][] adn = new char[dna.length][];

        String matrixHorizontal = "";
        for(int i =0;dna.length -1 >= i; i++){
            matrixHorizontal += dna[i];
            adn[i] = dna[i].toCharArray();
        }
        String hash = DigestUtils
                .md5Hex(matrixHorizontal).toUpperCase();

        Adn adnFind = adnService.findById(hash);
        if(adnFind != null){
            return adnFind.getMutant();
        }

        secuencesFound = 0;
        boolean isMutant = false;
        firstLoop:
        for(int i = 0; i < adn.length; i++ ) {
            for(int j = 0; j <  adn.length; j++ ) {
                if(i == 0) {
                    if( searchVertica(adn, i,j,adn[i][j],1) ||
                            searchDiagonal(adn, i,j,adn[i][j],1) ||
                                searchInverseDiagonal(adn, i,j,adn[i][j],1)) {
                        isMutant = true;
                        break firstLoop;
                    }
                }
                if(j == 0) {
                    if(i != 0){
                        if( searchHorizontal(adn, i,j,adn[i][j],1) ||
                                searchDiagonal(adn, i,j,adn[i][j],1)) {
                            isMutant = true;
                            break;
                        }
                    }else{
                        if( searchHorizontal(adn, i,j,adn[i][j],1)) {
                            isMutant = true;
                            break;
                        }

                    }
                }
                if(j == adn.length - 1 && i != 0) {
                    if(searchInverseDiagonal(adn, i,j,adn[i][j],1)) {
                        isMutant = true;
                        break;
                    }

                }

            }
        }
        Adn adnRegister = new Adn();
        adnRegister.setId(hash);
        adnRegister.setMutant(isMutant);
        adnRegister.setAdn(adn);
        adnService.save(adnRegister);

        return isMutant;



    }

    public boolean searchVertica(char[][] adn, int x, int y, char charAnterior, int coincidencia){
        if (x >= adn.length - 1) {
            return false;
        }
        char charActual = adn[x + 1][y];
        if(charActual == charAnterior){
            if(coincidencia + 1 == 4 ){
                secuencesFound++;
                if(secuencesFound == 2){
                    return true;
                }else{
                    return searchVertica(adn, x + 2,y,charActual,coincidencia+1);
                }
            }else{
                return searchVertica(adn, x + 1,y,charActual,coincidencia+1);
            }
        }else{
            return searchVertica(adn, x + 1,y,charActual,1);
        }


    }

    public boolean searchHorizontal(char[][] adn, int x,int y,char charAnterior, int coincidencia){
        if (y == adn.length - 1) {
            return false;
        }
        char charActual = adn[x][y+1];
        if(charActual == charAnterior){
            if(coincidencia + 1 == 4 ){
                secuencesFound++;
                if(secuencesFound == 2){
                    return true;
                }else{
                    return searchHorizontal(adn, x ,y + 2,charActual,coincidencia+1);
                }
            }else{
                return searchHorizontal(adn, x ,y + 1,charActual,coincidencia+1);
            }
        }else{
            return searchHorizontal(adn, x ,y + 1,charActual,1);
        }
    }

    public boolean searchDiagonal(char[][] adn, int x,int y,char charAnterior, int coincidencia){
        if (x >= adn.length - 1  || y >= adn.length - 1 ) {
            return false;
        }
        char charActual = adn[x + 1][y + 1];
        if(charActual == charAnterior){
            if(coincidencia + 1 == 4 ){
                secuencesFound++;
                if(secuencesFound == 2){
                    return true;
                }else{
                    return searchDiagonal(adn,x + 2,y +2,charActual,coincidencia+1);
                }
            }else{
                return searchDiagonal(adn,x + 1,y +1,charActual,coincidencia+1);
            }

        }else{
            return searchDiagonal(adn, x + 1,y + 1,charActual,1);
        }
    }

    public boolean searchInverseDiagonal(char[][] adn, int x,int y,char charAnterior, int coincidencia){
//        System.out.println("Buscar diagonal arriba" + x + " - " + y);
        if (y -1 <= 0 ||  x >= adn.length - 1 ) {
            return false;
        }
        char charActual = adn[x + 1][y - 1];
        if(charActual == charAnterior){
            if(coincidencia + 1 == 4 ){
                secuencesFound++;
                if(secuencesFound == 2){
                    return true;
                }else{
                    return searchInverseDiagonal(adn,x + 2,y - 2,charActual,coincidencia+1);
                }
            }else{
                return searchInverseDiagonal(adn,x + 1,y - 1,charActual,coincidencia+1);
            }

        }else{
            return searchInverseDiagonal(adn,x + 1,y - 1,charActual,1);
        }
    }

    @Override
    public Stats getStats(){
        Stats stats = adnRepositoryCustom.getStats();
        if(stats.getCountHumanDna() == null || stats.getCountHumanDna() == 0){
            stats.setRatio(1d);
        }else {
            Double ratio = stats.getCountMutantDna() *1.0d /stats.getCountHumanDna();
            stats.setRatio(ratio);
        }
        return stats;
    }

}
