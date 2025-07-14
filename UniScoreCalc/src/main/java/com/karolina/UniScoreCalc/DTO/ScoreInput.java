package com.karolina.UniScoreCalc.DTO;

import java.util.Map;

public class ScoreInput {
    private Long specialtyId;
    private Map<String, Double> subjectScores;

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public Map<String, Double> getSubjectScores() {
        return subjectScores;
    }

    public void setSubjectScores(Map<String, Double> subjectScores) {
        this.subjectScores = subjectScores;
    }
}
