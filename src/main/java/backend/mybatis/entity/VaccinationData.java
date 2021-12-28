package backend.mybatis.entity;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import com.csvreader.CsvWriter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;

@Getter
@Setter
public class VaccinationData implements Serializable {
    String COUNTRY;
    String ISO3;
    String WHO_REGION;
    String DATA_SOURCE;
    LocalDate DATE_UPDATED;
    double TOTAL_VACCINATIONS;
    double PERSONS_VACCINATED_1PLUS_DOSE;
    double TOTAL_VACCINATIONS_PER100;
    double PERSONS_VACCINATED_1PLUS_DOSE_PER100;
    double PERSONS_FULLY_VACCINATED;
    double PERSONS_FULLY_VACCINATED_PER100;
    String VACCINES_USED;
    LocalDate FIRST_VACCINE_DATE;
    int NUMBER_VACCINES_TYPES_USED;

    @CrossOrigin
    public static void download() throws Exception {
        String downLoadPath = UseMsedge.downloadLocation + "vaccination-data.csv";
        String path = "src/main/java/backend/tables/VaccinationData.csv";
        URL url = new URL("https://covid19.who.int/who-data/vaccination-data.csv");
        UseMsedge.openBrowser(url.toString());
        UseMsedge.closeBrowse();
        UseMsedge.moveFile(downLoadPath, path);
    }

    public static ArrayList<VaccinationData> update() throws Exception {
        ArrayList<VaccinationData> res = new ArrayList<>();
        String path = "src/main/java/backend/tables/VaccinationData.csv";
        String charset = "utf-8";
        String[] trace = null;
        FileInputStream fin = new FileInputStream(path);
        try (CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(fin, charset))).build()) {
            Iterator<String[]> iterator = csvReader.iterator();
            iterator.next();
            while (iterator.hasNext()) {
                String[] colums = iterator.next();
                for (int i = 0; i < colums.length; i++) {
                    if (colums[i].isEmpty() || Objects.equals(colums[i], " ")) {
                        colums[i] = "0";
                    }
                }
                VaccinationData tem = new VaccinationData();
                tem.COUNTRY = colums[0];
                tem.ISO3 = colums[1];
                tem.WHO_REGION = colums[2];
                tem.DATA_SOURCE = colums[3];
                tem.DATE_UPDATED = LocalDate.parse(colums[4]);
                tem.TOTAL_VACCINATIONS = Double.parseDouble(colums[5]);
                tem.PERSONS_VACCINATED_1PLUS_DOSE = Double.parseDouble(colums[6]);
                tem.TOTAL_VACCINATIONS_PER100 = Double.parseDouble(colums[7]);
                tem.PERSONS_VACCINATED_1PLUS_DOSE_PER100 = Double.parseDouble(colums[8]);
                tem.PERSONS_FULLY_VACCINATED = Integer.parseInt(colums[9]);
                tem.PERSONS_FULLY_VACCINATED_PER100 = Double.parseDouble(colums[10]);
                tem.VACCINES_USED = colums[11];
                if (colums[12].equals("0")) {
                    tem.FIRST_VACCINE_DATE = null;
                } else {
                    tem.FIRST_VACCINE_DATE = LocalDate.parse(colums[12]);
                }
                tem.NUMBER_VACCINES_TYPES_USED = Integer.parseInt(colums[13]);
                res.add(tem);

            }
            fin.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }
}
