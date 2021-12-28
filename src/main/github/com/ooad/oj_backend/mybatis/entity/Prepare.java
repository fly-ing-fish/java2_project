package com.ooad.oj_backend.mybatis.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Prepare {
    public static List<DataRow> prepareData() {
        List<DataRow> dataRows = new LinkedList<>();
        String path = "owid-covid-data.csv";
        try {
            FileReader f = new FileReader(path);
            BufferedReader br = new BufferedReader(f);
            String row = br.readLine();
            row = br.readLine();
            int index = 0;
            while (row != null) {
                index++;
                if (row.endsWith(",")) {
                    row += " ";
                }
                String[] colums = row.split(",");

                for (int i = 0; i < colums.length; i++) {
                    if (colums[i].isEmpty() || Objects.equals(colums[i], " ")) {
                        colums[i] = "0";
                    }
                }
                DataRow tem = new DataRow();
                tem.iso_code = colums[0];
                tem.continent = colums[1];
                tem.location = colums[2];
                tem.date = colums[3];
                tem.total_cases = Double.parseDouble(colums[4]);
                tem.new_cases = Double.parseDouble(colums[5]);
                tem.new_cases_smoothed = Double.parseDouble(colums[6]);
                tem.total_deaths = Double.parseDouble(colums[7]);
                tem.new_deaths = Double.parseDouble(colums[8]);
                tem.new_deaths_smoothed = Double.parseDouble(colums[9]);
                tem.total_cases_per_million = Double.parseDouble(colums[10]);
                tem.new_cases_per_million = Double.parseDouble(colums[11]);
                tem.new_cases_smoothed_per_million = Double.parseDouble(colums[12]);
                tem.total_deaths_per_million = Double.parseDouble(colums[13]);
                tem.new_deaths_per_million = Double.parseDouble(colums[14]);
                tem.new_deaths_smoothed_per_million = Double.parseDouble(colums[15]);
                tem.reproduction_rate = Double.parseDouble(colums[16]);
                tem.icu_patients = Double.parseDouble(colums[17]);
                tem.icu_patients_per_million = Double.parseDouble(colums[18]);
                tem.hosp_patients = Double.parseDouble(colums[19]);
                tem.hosp_patients_per_million = Double.parseDouble(colums[20]);
                tem.weekly_icu_admissions = Double.parseDouble(colums[21]);
                tem.weekly_icu_admissions_per_million = Double.parseDouble(colums[22]);
                tem.weekly_hosp_admissions = Double.parseDouble(colums[23]);
                tem.weekly_hosp_admissions_per_million = Double.parseDouble(colums[24]);
                tem.new_tests = Double.parseDouble(colums[25]);
                tem.total_tests = Double.parseDouble(colums[26]);
                tem.total_tests_per_thousand = Double.parseDouble(colums[27]);
                tem.new_tests_per_thousand = Double.parseDouble(colums[28]);
                tem.new_tests_smoothed = Double.parseDouble(colums[29]);
                tem.new_tests_smoothed_per_thousand = Double.parseDouble(colums[30]);
                tem.positive_rate = Double.parseDouble(colums[31]);
                tem.tests_per_case = Double.parseDouble(colums[32]);
                tem.tests_units = colums[33];
                tem.total_vaccinations = Double.parseDouble(colums[34]);
                tem.people_vaccinated = Double.parseDouble(colums[35]);
                tem.people_fully_vaccinated = Double.parseDouble(colums[36]);
                tem.total_boosters = Double.parseDouble(colums[37]);
                tem.new_vaccinations = Double.parseDouble(colums[38]);
                tem.new_vaccinations_smoothed = Double.parseDouble(colums[39]);
                tem.total_vaccinations_per_hundred = Double.parseDouble(colums[40]);
                tem.people_vaccinated_per_hundred = Double.parseDouble(colums[41]);
                tem.people_fully_vaccinated_per_hundred = Double.parseDouble(colums[42]);
                tem.total_boosters_per_hundred = Double.parseDouble(colums[43]);
                tem.new_vaccinations_smoothed_per_million = Double.parseDouble(colums[44]);
                tem.stringency_index = Double.parseDouble(colums[45]);
                tem.population = Double.parseDouble(colums[46]);
                tem.population_density = Double.parseDouble(colums[47]);
                tem.median_age = Double.parseDouble(colums[48]);
                tem.aged_65_older = Double.parseDouble(colums[49]);
                tem.aged_70_older = Double.parseDouble(colums[50]);
                tem.gdp_per_capita = Double.parseDouble(colums[51]);
                tem.extreme_poverty = Double.parseDouble(colums[52]);
                tem.cardiovasc_death_rate = Double.parseDouble(colums[53]);
                tem.diabetes_prevalence = Double.parseDouble(colums[54]);
                tem.female_smokers = Double.parseDouble(colums[55]);
                tem.male_smokers = Double.parseDouble(colums[56]);
                tem.handwashing_facilities = Double.parseDouble(colums[57]);
                tem.hospital_beds_per_thousand = Double.parseDouble(colums[58]);
                tem.life_expectancy = Double.parseDouble(colums[59]);
                tem.human_development_index = Double.parseDouble(colums[60]);
                tem.excess_mortality_cumulative_absolute = Double.parseDouble(colums[61]);
                tem.excess_mortality_cumulative = Double.parseDouble(colums[62]);
                tem.excess_mortality = Double.parseDouble(colums[63]);
                tem.excess_mortality_cumulative_per_million = Double.parseDouble(colums[64]);
                dataRows.add(tem);
                row = br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataRows;
    }
}
