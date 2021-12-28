import backend.mybatis.entity.DailyCasesAndDeaths;
import backend.mybatis.entity.LatestCasesAndDeaths;
import backend.mybatis.entity.VaccinationData;
import backend.mybatis.entity.VaccinationMetadata;

import java.lang.reflect.Field;

public class TH {
    public static void main(String[] args) {
        Class<?>temp= LatestCasesAndDeaths.class;
        for(Field field:temp.getDeclaredFields())
            System.out.println("<option>"+field.getName()+"</option>");
        for(Field field:temp.getDeclaredFields())
            System.out.println("<th>"+field.getName()+"</th>");
        for(int i=0;i<temp.getDeclaredFields().length;++i)
            System.out.println("<td>"+"</td>");
    }
}
