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

import java.io.Serializable;

@Getter
@Setter
public class VaccinationData implements Serializable {
    String COUNTRY;
    String ISO3;
    String WHO_REGION;
    String DATA_SOURCE;
    LocalDate DATE_UPDATED;
    int TOTAL_VACCINATIONS;
    double PERSONS_VACCINATED_1PLUS_DOSE;
    int TOTAL_VACCINATIONS_PER100;
    double PERSONS_VACCINATED_1PLUS_DOSE_PER100;
    int PERSONS_FULLY_VACCINATED;
    double PERSONS_FULLY_VACCINATED_PER100;
    String VACCINES_USED;
    LocalDate FIRST_VACCINE_DATE;
    int NUMBER_VACCINES_TYPES_USED;

    public static ArrayList<VaccinationData> download() throws IOException {
        ArrayList<VaccinationData> res = new ArrayList<>();
        String path = "src/main/java/backend/tables/VaccinationData_tem.csv";
        String path1 = "src/main/java/backend/tables/VaccinationData.csv";
        URL url = new URL("https://covid19.who.int/who-data/vaccination-data.csv");

        File f = new File(path);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        OutputStream outputStream = new FileOutputStream(f);
        byte[] bytes = new byte[1024*1024];
        int byteCount = 0;
        while ((byteCount = bis.read(bytes)) != -1) {
            outputStream.write(bytes, 0, byteCount);
        }
        bis.close();
        outputStream.close();

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
                VaccinationData tem = new VaccinationData();
                tem.COUNTRY = colums[0];
                tem.ISO3 = colums[1];
                tem.WHO_REGION = colums[2];
                tem.DATA_SOURCE = colums[3];
                tem.DATE_UPDATED = LocalDate.parse(colums[4]);
                tem.TOTAL_VACCINATIONS = Integer.parseInt(colums[5]);
                tem.PERSONS_VACCINATED_1PLUS_DOSE = Double.parseDouble(colums[6]);
                tem.TOTAL_VACCINATIONS_PER100 = Integer.parseInt(colums[7]);
                tem.PERSONS_VACCINATED_1PLUS_DOSE_PER100 = Double.parseDouble(colums[8]);
                tem.PERSONS_FULLY_VACCINATED = Integer.parseInt(colums[9]);
                tem.PERSONS_FULLY_VACCINATED_PER100 = Double.parseDouble(colums[10]);
                tem.VACCINES_USED = colums[11];
                tem.FIRST_VACCINE_DATE = LocalDate.parse(colums[12]);
                tem.NUMBER_VACCINES_TYPES_USED = Integer.parseInt(colums[13]);
                res.add(tem);

            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }
}
