package com.ml.adnmutant.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Arrays;

@Document(indexName = "register", type = "adn")
public class Adn {

    @Id
    private String id;
    @Field(type = FieldType.Boolean)
    private Boolean mutant;
    private char[][] adn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getMutant() {
        return mutant;
    }

    public void setMutant(Boolean mutant) {
        this.mutant = mutant;
    }

    public char[][] getAdn() {
        return adn;
    }

    public void setAdn(char[][] adn) {
        this.adn = adn;
    }

    @Override
    public String toString() {
        return "Adn{" +
                "id='" + id + '\'' +
                ", isMutant=" + mutant +
                ", adn=" + Arrays.toString(adn) +
                '}';
    }
}
