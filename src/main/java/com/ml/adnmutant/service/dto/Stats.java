package com.ml.adnmutant.service.dto;

public class Stats {

    private Long countMutantDna;
    private Long countHumanDna;
    private Double ratio;

    public Long getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(Long countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public Long getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(Long countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "countMutantDna=" + countMutantDna +
                ", countHumanDna=" + countHumanDna +
                ", ratio=" + ratio +
                '}';
    }
}
