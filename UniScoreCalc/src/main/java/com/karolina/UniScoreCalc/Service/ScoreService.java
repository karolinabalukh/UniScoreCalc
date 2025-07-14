package com.karolina.UniScoreCalc.Service;

import com.karolina.UniScoreCalc.DTO.ScoreInput;
import com.karolina.UniScoreCalc.Model.SpecialtyCoefficient;
import com.karolina.UniScoreCalc.Repository.SpecialtyCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService {

    @Autowired
    private SpecialtyCoefficientRepository repository;

    public double calculateScoreDynamic(Long specialtyId, Map<String, Double> subjectScores) {
        SpecialtyCoefficient sc = repository.findById(specialtyId)
                .orElseThrow(() -> new RuntimeException("Specialty not found"));

        Map<String, Double> subjectCoefficients = Map.of(
                "ukr_mova", sc.getUkr_mova(),
                "math", sc.getMath(),
                "history", sc.getHistory(),
                "language", sc.getLanguage(),
                "biology", sc.getBiology(),
                "physics", sc.getPhysics(),
                "chemistry", sc.getChemistry(),
                "literature", sc.getLiterature(),
                "geography", sc.getGeography()
        );

        double numerator = 0;
        double denominator = 0;

        double k4max = subjectCoefficients.values().stream().max(Double::compare).orElse(0.0);

        int i = 0;
        for (Map.Entry<String, Double> entry : subjectScores.entrySet()) {
            String subject = entry.getKey();
            Double score = entry.getValue();
            Double coef = subjectCoefficients.getOrDefault(subject, 0.0);

            if (i < 3) {
                numerator += score * coef;
                denominator += coef;
            } else {
                numerator += score * coef;
                denominator += (coef + k4max) / 2;
            }
            i++;
        }

        return numerator / denominator;
    }

}
