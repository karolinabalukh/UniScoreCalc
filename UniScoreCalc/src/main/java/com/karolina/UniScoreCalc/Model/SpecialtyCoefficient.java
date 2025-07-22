package com.karolina.UniScoreCalc.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "specialty_coefficients")
public class SpecialtyCoefficient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;
    private String name;

    @Column(name = "ukr_mova")
    private Double ukr_mova;

    @Column(name = "math")
    private Double math;

    @Column(name = "history")
    private Double history;
    private Double language;

    private Double biology;
    private Double physics;
    private Double chemistry;
    private Double literature;
    private Double geography;



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getUkr_mova() {
        return ukr_mova;
    }

    public Double getMath() {
        return math;
    }

    public Double getHistory() {
        return history;
    }

    public Double getLanguage() {
        return language;
    }

    public Double getBiology() {
        return biology;
    }

    public Double getPhysics() {
        return physics;
    }

    public Double getChemistry() {
        return chemistry;
    }

    public Double getLiterature() {
        return literature;
    }

    public Double getGeography() {
        return geography;
    }



    public String getCode() {
        return code;
    }


}
