package com.ml.adnmutant.rest;

import com.ml.adnmutant.service.IsMutantService;
import com.ml.adnmutant.service.dto.Dna;
import com.ml.adnmutant.service.dto.Stats;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1")
public class AdnMutantResource {

    private final IsMutantService isMutantService;

    public AdnMutantResource(IsMutantService isMutantService){
        this.isMutantService = isMutantService;
    }

    /**
     * Check is MUTANT
     *
     * @param adn the List<String>
     * @return the responseEntity
     */
    @PostMapping("/mutant")
    public ResponseEntity isMutant(@Valid @RequestBody Dna dna) {
        Pattern p = Pattern.compile("^[ATCG]+$");
        boolean adnOk = false;
        int adnSize = dna.getDna().length;
        for(String cadena:dna.getDna()){
            Matcher m = p.matcher(cadena);

            adnOk = m.find() && (adnSize == cadena.length());
            if(!adnOk){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }

        if(isMutantService.isMutant(dna.getDna())){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/stats")
    public ResponseEntity<Stats> getStats(){

        return  ResponseEntity.ok(isMutantService.getStats()) ;

    }
}
