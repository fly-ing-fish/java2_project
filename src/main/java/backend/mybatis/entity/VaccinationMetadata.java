package backend.mybatis.entity;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;

@Getter
@Setter
public class VaccinationMetadata implements Serializable {
    String ISO3;
    String VACCINE_NAME;
    String PRODUCT_NAME;
    String COMPANY_NAME;
    LocalDate FIRST_VACCINE_DATE;
    LocalDate AUTHORIZATION_DATE;
    LocalDate START_DATE;
    LocalDate END_DATE;
    String COMMENT;
    String DATA_SOURCE;

    public static void download() throws Exception {
        String downLoadPath = UseMsedge.downloadLocation + "vaccination-metadata.csv";
        String path = "src/main/java/backend/tables/VaccinationMetadata.csv";
        URL url = new URL("https://covid19.who.int/who-data/vaccination-metadata.csv");
        UseMsedge.openBrowser(url.toString());
        UseMsedge.closeBrowse();
        UseMsedge.moveFile(downLoadPath, path);
    }

    public static ArrayList<VaccinationMetadata> update() throws Exception {
        ArrayList<VaccinationMetadata> res = new ArrayList<>();
        String path = "src/main/java/backend/tables/VaccinationMetadata.csv";
        String charset = "utf-8";
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
                VaccinationMetadata tem = new VaccinationMetadata();
                tem.ISO3 = colums[0];
                tem.VACCINE_NAME = colums[1];
                tem.PRODUCT_NAME = colums[2];
                tem.COMPANY_NAME = colums[3];
//                if(colums[4].equals("0")){
//                    tem.FIRST_VACCINE_DATE =null;
//                }else {
//                    tem.FIRST_VACCINE_DATE = LocalDate.parse(colums[4]);
//                }
                if(colums[4].equals("0")){
                    tem.AUTHORIZATION_DATE =null;
                }else {
                    tem.AUTHORIZATION_DATE = LocalDate.parse(colums[4]);
                }
                if(colums[5].equals("0")){
                    tem.START_DATE =null;
                }else {
                    tem.START_DATE = LocalDate.parse(colums[5]);
                }
                if(colums[6].equals("0")){
                    tem.END_DATE = null;
                }else {
                    tem.END_DATE = LocalDate.parse(colums[6]);
                }

                tem.COMMENT = colums[7];
                tem.DATA_SOURCE = colums[8];
                res.add(tem);

            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
