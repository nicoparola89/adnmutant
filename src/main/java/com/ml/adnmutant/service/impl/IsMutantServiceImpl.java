package com.ml.adnmutant.service.impl;

import com.ml.adnmutant.domain.Adn;
import com.ml.adnmutant.repository.AdnRepositoryCustom;
import com.ml.adnmutant.service.AdnService;
import com.ml.adnmutant.service.IsMutantService;
import com.ml.adnmutant.service.dto.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class IsMutantServiceImpl implements IsMutantService {

    private final AdnService adnService;

    @Autowired
    private AdnRepositoryCustom adnRepositoryCustom;

    public IsMutantServiceImpl(AdnService adnService ) {
        this.adnService = adnService;
    }

    @Override
    public boolean isMutant(char[][] adn) {

        boolean isMutant = false;
        for(int i = 0; i < adn.length; i++ ) {
            for(int j = 0; j <  adn.length; j++ ) {
                if(i == 0) {
                    if( buscarOrizontal(adn, i,j,adn[i][j],1) || buscarDiagonalAbajo(adn, i,j,adn[i][j],1) ||
                            buscarDiagonalArriba(adn, i,j,adn[i][j],1)) {
                        isMutant = true;
                        break;
                    }
                }
                if(j == 0) {
                    if( buscarVertical(adn, i,j,adn[i][j],1) ||  buscarDiagonalAbajo(adn, i,j,adn[i][j],1)) {
                        isMutant = true;
                        break;
                    }

                }
                if(j == adn.length - 1) {
                    if(buscarDiagonalArriba(adn, i,j,adn[i][j],1)) {
                        isMutant = true;
                        break;
                    }

                }

            }
        }
        Adn adnRegister = new Adn();
        adnRegister.setMutant(isMutant);
        adnRegister.setAdn(adn);
        adnService.save(adnRegister);

        return isMutant;



    }

    public boolean buscarOrizontal(char[][] adn, int x, int y, char charAnterior, int coincidencia){
        //System.out.println(x + " - " + y);
        if (x == adn.length - 1) {
            return false;
        }
        char charActual = adn[x + 1][y];
        if(charActual == charAnterior){
            if(coincidencia + 1 == 4 ){
                return true;
            }else{
                return buscarOrizontal(adn, x + 1,y,charActual,coincidencia+1);
            }

        }else{
            return buscarOrizontal(adn, x + 1,y,charActual,1);
        }


    }

    public boolean buscarVertical(char[][] adn, int x,int y,char charAnterior, int coincidencia){
        //System.out.println(x + " - " + y);
        if (y == adn.length - 1) {
            return false;
        }
        char charActual = adn[x][y+1];
        if(charActual == charAnterior){
            if(coincidencia + 1 == 4 ){
                return true;
            }else{
                return buscarVertical(adn, x ,y + 1,charActual,coincidencia+1);
            }

        }else{
            return buscarVertical(adn, x ,y + 1,charActual,1);
        }
    }

    public boolean buscarDiagonalAbajo(char[][] adn, int x,int y,char charAnterior, int coincidencia){
        //System.out.println(x + " - " + y);
        if (x == adn.length - 1  || y == adn.length - 1 ) {
            return false;
        }
        char charActual = adn[x + 1][y + 1];
        if(charActual == charAnterior){
            if(coincidencia + 1 == 4 ){
                return true;
            }else{
                return buscarDiagonalAbajo(adn,x + 1,y +1,charActual,coincidencia+1);
            }

        }else{
            return buscarDiagonalAbajo(adn, x + 1,y + 1,charActual,1);
        }
    }

    public boolean buscarDiagonalArriba(char[][] adn, int x,int y,char charAnterior, int coincidencia){
        //System.out.println(x + " - " + y);
        if (y -1 < 0 ||  x == adn.length - 1 ) {
            return false;
        }
        char charActual = adn[x + 1][y - 1];
        if(charActual == charAnterior){
            if(coincidencia + 1 == 4 ){
                return true;
            }else{
                return buscarDiagonalArriba(adn,x + 1,y - 1,charActual,coincidencia+1);
            }

        }else{
            return buscarDiagonalArriba(adn,x + 1,y - 1,charActual,1);
        }
    }

    @Override
    public Stats getStats(){
        Stats stats = adnRepositoryCustom.getStats();
        if(stats.getCountHumanDna() == 0){
            stats.setRatio(1d);
        }else {
            Double ratio = stats.getCountMutantDna() *1.0d /stats.getCountHumanDna();
            stats.setRatio(ratio);
        }
        return stats;
    }

}
