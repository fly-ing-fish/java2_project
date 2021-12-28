package backend.mybatis.entity;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class DailyCasesAndDeaths implements Serializable {
    LocalDate Date_reported;
    String Country_code;
    String Country;
    String WHO_region;
    int New_cases;
    int Cumulative_cases;
    int New_deaths;
    int Cumulative_deaths;

    public static void download() throws IOException {

        String path = "src/main/java/backend/tables/DailyCasesAndDeaths.csv";
        URL url = new URL("https://covid19.who.int/WHO-COVID-19-global-data.csv");
        InputStream inputStream = url.openStream();
        File f = new File(path);
        OutputStream outputStream = new FileOutputStream(f);
        int byteCount = 0;
        byte[] bytes = new byte[1024 * 1024];
        while ((byteCount = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, byteCount);
        }
        inputStream.close();
        outputStream.close();
    }
    public static ArrayList<DailyCasesAndDeaths> update() throws IOException {
        ArrayList<DailyCasesAndDeaths> res = new ArrayList<>();
        String path = "src/main/java/backend/tables/DailyCasesAndDeaths.csv";
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
                DailyCasesAndDeaths tem = new DailyCasesAndDeaths();
                tem.Date_reported = LocalDate.parse(colums[0]);
                tem.Country_code = colums[1];
                tem.Country = colums[2];
                tem.WHO_region = colums[3];
                tem.New_cases = Integer.parseInt(colums[4]);
                tem.Cumulative_cases = Integer.parseInt(colums[5]);
                tem.New_deaths = Integer.parseInt(colums[6]);
                tem.Cumulative_deaths = Integer.parseInt(colums[7]);
                res.add(tem);
            }
            return res;
        } catch (Exception e) {
            System.out.println(Arrays.toString(trace));
            e.printStackTrace();
        }
        return res;
    }

}
