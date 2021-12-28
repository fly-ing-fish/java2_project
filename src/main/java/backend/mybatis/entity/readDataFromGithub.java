package backend.mybatis.entity;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class readDataFromGithub {
    public static ArrayList<DailyCasesAndDeaths> dailyCasesAndDeaths = new ArrayList<>();
    public static ArrayList<LatestCasesAndDeaths> latestCasesAndDeaths = new ArrayList<>();
    public static ArrayList<VaccinationData> vaccinationData = new ArrayList<>();
    //public static ArrayList<VaccinationMetadata> vaccinationMetadata = new ArrayList<>();

    public static void download() throws Exception {
        String downLoadPath = UseMsedge.downloadLocation + "owid-covid-data.csv";
        String path = "src/main/java/backend/tables/owidcoviddata.csv";
        URL url = new URL("https://covid.ourworldindata.org/data/owid-covid-data.csv");
        UseMsedge.openBrowser(url.toString());
        UseMsedge.closeBrowse();
        UseMsedge.moveFile(downLoadPath, path);

        String charset = "utf-8";
        String[] trace =null;
        try (CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), charset))).build()) {
            Iterator<String[]> iterator = csvReader.iterator();
            iterator.next();
            while (iterator.hasNext()) {
                String[] colums = iterator.next();
                for (int i = 0; i < colums.length; i++) {
                    if (colums[i].isEmpty() || Objects.equals(colums[i], " ")) {
                        colums[i] = "0";
                    }
                }
                trace = colums;

                DailyCasesAndDeaths tem1 = new DailyCasesAndDeaths();
                tem1.Date_reported = LocalDate.parse(colums[3]);
                tem1.Country_code = colums[0];
                tem1.Country = colums[2];
                tem1.WHO_region = null;
                tem1.New_cases = Integer.parseInt(colums[5].split("\\..")[0]);
                tem1.Cumulative_cases = Integer.parseInt(colums[4].split("\\..")[0]);
                tem1.New_deaths = Integer.parseInt(colums[8].split("\\..")[0]);
                tem1.Cumulative_deaths = Integer.parseInt(colums[7].split("\\..")[0]);
                dailyCasesAndDeaths.add(tem1);

                LatestCasesAndDeaths tem2 = new LatestCasesAndDeaths();
                tem2.Name = colums[2];
                tem2.WHO_region = null;
                tem2.CasesCumulativeTotal = Integer.parseInt(colums[4].split("\\.")[0]);
                tem2.CasesCumulativeTotalPer100000 = Double.parseDouble(colums[10].split("\\..")[0]) / 10;
                tem2.CasesNewlyIn7days = 0;
                tem2.CasesNewlyIn7daysPer100000 = 0;
                tem2.CasesReportedIn24hours = 0;
                tem2.DeathsCumulativeTotal = Integer.parseInt(colums[7].split("\\..")[0]);
                tem2.DeathsCumulativeTotalPer100000 = Double.parseDouble(colums[13]) / 10;
                tem2.DeathsNewlyIn7days = 0;
                tem2.DeathsNewlyIn7daysPer100000 = 0;
                tem2.DeathsReportedIn24hours = 0;
                latestCasesAndDeaths.add(tem2);

                VaccinationData tem3 = new VaccinationData();
                tem3.COUNTRY = colums[2];
                tem3.ISO3 = colums[0];
                tem3.WHO_REGION = null;
                tem3.DATA_SOURCE = null;
                tem3.DATE_UPDATED = LocalDate.parse(colums[3]);
                tem3.TOTAL_VACCINATIONS = Double.parseDouble(colums[34]);
                tem3.PERSONS_VACCINATED_1PLUS_DOSE = Double.parseDouble(colums[35]);
                tem3.TOTAL_VACCINATIONS_PER100 = Double.parseDouble(colums[40]);
                tem3.PERSONS_VACCINATED_1PLUS_DOSE_PER100 = 0;
                tem3.PERSONS_FULLY_VACCINATED = Double.parseDouble(colums[36].split("\\..")[0]);
                tem3.PERSONS_FULLY_VACCINATED_PER100 = Double.parseDouble(colums[41]);
                tem3.VACCINES_USED = null;
                if (colums[12].equals("0")) {
                    tem3.FIRST_VACCINE_DATE = null;
                } else {
                    tem3.FIRST_VACCINE_DATE = null;
                }
                tem3.NUMBER_VACCINES_TYPES_USED = 0;
                vaccinationData.add(tem3);


                // VaccinationMetadata tem4 = new VaccinationMetadata();
                // tem4.ISO3 = colums[0];
                // tem4.VACCINE_NAME = null;
                // tem4.PRODUCT_NAME = null;
                // tem4.COMPANY_NAME =null;
//                if(colums[4].equals("0")){
//                    tem.FIRST_VACCINE_DATE =null;
//                }else {
//                    tem.FIRST_VACCINE_DATE = LocalDate.parse(colums[4]);
//                }
              /*  if(colums[4].equals("0")){
                    tem4.AUTHORIZATION_DATE =null;
                }else {
                    tem4.AUTHORIZATION_DATE = null;
                }
                if(colums[5].equals("0")){
                    tem4.START_DATE =null;
                }else {
                    tem4.START_DATE  = null;
                }
                if(colums[6].equals("0")){
                    tem4.END_DATE = null;
                }else {
                    tem4.END_DATE = null;
                }

                tem4.COMMENT  = null;
                tem4.DATA_SOURCE  = null;
                vaccinationMetadata.add(tem4);
            }*/
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(trace));
            e.printStackTrace();
        }
    }

}
