package backend.mybatis.entity;

import java.io.IOException;
import java.util.ArrayList;

public class readDataFromWho {
    public static ArrayList<DailyCasesAndDeaths> dailyCasesAndDeaths = new ArrayList<>();
    public static ArrayList<LatestCasesAndDeaths> latestCasesAndDeaths = new ArrayList<>();
    public static ArrayList<VaccinationData> vaccinationData = new ArrayList<>();
   // public static ArrayList<VaccinationMetadata> vaccinationMetadata = new ArrayList<>();

    public static void download() throws Exception {
        dailyCasesAndDeaths = DailyCasesAndDeaths.download();
        latestCasesAndDeaths = LatestCasesAndDeaths.download();
        vaccinationData = VaccinationData.download();
     //   vaccinationMetadata = VaccinationMetadata.download();
    }
}
