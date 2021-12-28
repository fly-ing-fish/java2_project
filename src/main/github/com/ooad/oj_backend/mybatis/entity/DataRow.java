package com.ooad.oj_backend.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DataRow implements Serializable {
    String iso_code;
    String continent;
    String location;
    String date;
    double total_cases;
    double new_cases;
    double new_cases_smoothed;
    double total_deaths;
    double new_deaths;
    double new_deaths_smoothed;
    double total_cases_per_million;
    double new_cases_per_million;
    double new_cases_smoothed_per_million;
    double total_deaths_per_million;
    double new_deaths_per_million;
    double new_deaths_smoothed_per_million;
    double reproduction_rate;
    double icu_patients;
    double icu_patients_per_million;
    double hosp_patients;
    double hosp_patients_per_million;
    double weekly_icu_admissions;
    double weekly_icu_admissions_per_million;
    double weekly_hosp_admissions;
    double weekly_hosp_admissions_per_million;
    double new_tests;
    double total_tests;
    double total_tests_per_thousand;
    double new_tests_per_thousand;
    double new_tests_smoothed;
    double new_tests_smoothed_per_thousand;
    double positive_rate;
    double tests_per_case;
    String tests_units;
    double total_vaccinations;
    double people_vaccinated;
    double people_fully_vaccinated;
    double total_boosters;
    double new_vaccinations;
    double new_vaccinations_smoothed;
    double total_vaccinations_per_hundred;
    double people_vaccinated_per_hundred;
    double people_fully_vaccinated_per_hundred;
    double total_boosters_per_hundred;
    double new_vaccinations_smoothed_per_million;
    double stringency_index;
    double population;
    double population_density;
    double median_age;
    double aged_65_older;
    double aged_70_older;
    double gdp_per_capita;
    double extreme_poverty;
    double cardiovasc_death_rate;
    double diabetes_prevalence;
    double female_smokers;
    double male_smokers;
    double handwashing_facilities;
    double hospital_beds_per_thousand;
    double life_expectancy;
    double human_development_index;
    double excess_mortality_cumulative_absolute;
    double excess_mortality_cumulative;
    double excess_mortality;
    double excess_mortality_cumulative_per_million;
}
