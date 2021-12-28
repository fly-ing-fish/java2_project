/*
package backend.mybatis.entity;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.Getter;
import lombok.Setter;

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

    public static ArrayList<VaccinationMetadata> download() throws IOException {
        ArrayList<VaccinationMetadata> res = new ArrayList<>();
        String path = "src/main/java/backend/tables/vaccination-metadata.csv";
        URL url = new URL("https://covid19.who.int/who-data/vaccination-metadata.csv");
        InputStream inputStream = url.openStream();
        File f = new File(path);
        OutputStream outputStream = new FileOutputStream(f);
        int byteCount = 0;
        byte[] bytes = new byte[1024*1024*1024];
        while ((byteCount = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, byteCount);
        }
        inputStream.close();
        outputStream.close();
        String charset = "utf-8";
        String trace = "";
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
                trace = Arrays.toString(colums);
                VaccinationMetadata tem = new VaccinationMetadata();
                tem.ISO3 = colums[0];
                tem.VACCINE_NAME = colums[1];
                tem.PRODUCT_NAME = colums[2];
                tem.COMPANY_NAME = colums[3];
                if(!colums[4].equals("0")) {
                    tem.FIRST_VACCINE_DATE = LocalDate.parse(colums[4]);
                }else tem.FIRST_VACCINE_DATE=null;
                if(!colums[5].equals("0")) {
                    tem.AUTHORIZATION_DATE = LocalDate.parse(colums[5]);
                }else tem.AUTHORIZATION_DATE=null;
                if(!colums[6].equals("0")) {
                    tem.START_DATE = LocalDate.parse(colums[6]);
                }else tem.START_DATE=null;
                if(!colums[7].equals("0")) {
                    tem.END_DATE = LocalDate.parse(colums[7]);
                }else tem.END_DATE=null;
                tem.COMMENT = colums[8];
                try {
                    tem.DATA_SOURCE = colums[9];
                }catch (Exception e){
                    tem.DATA_SOURCE=null;
                }
                res.add(tem);

            }
            return res;
        } catch (Exception e) {
            System.out.println(trace);
            e.printStackTrace();
        }
        return res;
    }
}
*/
