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

import java.io.Serializable;

@Getter
@Setter
public class LatestCasesAndDeaths implements Serializable {
    String Name;
    String WHO_region;
    int CasesCumulativeTotal;
    double CasesCumulativeTotalPer100000;
    int CasesNewlyIn7days;
    double CasesNewlyIn7daysPer100000;
    int CasesReportedIn24hours;
    int DeathsCumulativeTotal;
    double DeathsCumulativeTotalPer100000;
    int DeathsNewlyIn7days;
    double DeathsNewlyIn7daysPer100000;
    int DeathsReportedIn24hours;

    public static void download() throws IOException {
        String path = "src/main/java/backend/tables/LatestCasesAndDeaths.csv";
        URL url = new URL("https://covid19.who.int/WHO-COVID-19-global-table-data.csv");
        InputStream inputStream = url.openStream();
        File f = new File(path);
        OutputStream outputStream = new FileOutputStream(f);
        int byteCount = 0;
        byte[] bytes = new byte[1024 * 1024 * 1024];
        while ((byteCount = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, byteCount);
        }
        inputStream.close();
        outputStream.close();
    }
    public static ArrayList<LatestCasesAndDeaths> update() throws IOException {
        String path = "src/main/java/backend/tables/LatestCasesAndDeaths.csv";
        ArrayList<LatestCasesAndDeaths> res = new ArrayList<>();
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
                LatestCasesAndDeaths tem = new LatestCasesAndDeaths();
                tem.Name = colums[0];
                tem.WHO_region = colums[1];
                tem.CasesCumulativeTotal = Integer.parseInt(colums[2]);
                tem.CasesCumulativeTotalPer100000 = Double.parseDouble(colums[3]);
                tem.CasesNewlyIn7days = Integer.parseInt(colums[4]);
                tem.CasesNewlyIn7daysPer100000 = Double.parseDouble(colums[5]);
                tem.CasesReportedIn24hours = Integer.parseInt(colums[6]);
                tem.DeathsCumulativeTotal = Integer.parseInt(colums[7]);
                tem.DeathsCumulativeTotalPer100000 = Double.parseDouble(colums[8]);
                tem.DeathsNewlyIn7days = Integer.parseInt(colums[9]);
                tem.DeathsNewlyIn7daysPer100000 = Double.parseDouble(colums[10]);
                tem.DeathsReportedIn24hours = Integer.parseInt(colums[11]);
                res.add(tem);

            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
