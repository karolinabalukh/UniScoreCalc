package com.karolina.UniScoreCalc.Controller;

import com.karolina.UniScoreCalc.DTO.ScoreInput;
import com.karolina.UniScoreCalc.Model.SpecialtyCoefficient;
import com.karolina.UniScoreCalc.Repository.SpecialtyCoefficientRepository;
import com.karolina.UniScoreCalc.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CalculatorController {

    @Autowired
    private SpecialtyCoefficientRepository Srepository;

    @Autowired
    private ScoreService Sservice;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("specialtyCoefficients", Srepository.findAll());
        return "index";
    }

    @GetMapping("/table")
    public String showTable(Model model) {
        model.addAttribute("specialtyCoefficients", Srepository.findAll());
        return "table";
    }

    @GetMapping("/calc-score")
    public String showCalculator(Model model) {
        model.addAttribute("specialtyCoefficients", Srepository.findAll());
        return "calc-score";
    }


    @GetMapping("/get-coefs/{id}")
    @ResponseBody
    public Map<String, Double> getCoefficients(@PathVariable Long id) {
        SpecialtyCoefficient sc = Srepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Спеціальність не знайдена"));

        Map<String, Double> coefs = new HashMap<>();
        coefs.put("ukr_mova", sc.getUkr_mova());
        coefs.put("math", sc.getMath());
        coefs.put("history", sc.getHistory());
        coefs.put("language", sc.getLanguage());
        coefs.put("biology", sc.getBiology());
        coefs.put("physics", sc.getPhysics());
        coefs.put("chemistry", sc.getChemistry());
        coefs.put("literature", sc.getLiterature());
        coefs.put("geography", sc.getGeography());

        return coefs;
    }

    @PostMapping("/calc-score")
    @ResponseBody
    public double calculate(@RequestBody ScoreInput inp) {
        return Sservice.calculateScoreDynamic(inp.getSpecialtyId(), inp.getSubjectScores());
    }
}
