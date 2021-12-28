package backend.service;

import backend.mybatis.entity.*;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ChartService {
//    List<DataRow> dataRows = Prepare.prepareData();
    static ArrayList<DailyCasesAndDeaths> dataRows;
    static ArrayList<DailyCasesAndDeaths>storedData=new ArrayList<>();
    static {
        try {
            dataRows = DailyCasesAndDeaths.download();
            storedData.addAll(dataRows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<?> getLineInformation1() throws IOException {
        ArrayList<DailyCasesAndDeaths> dataRows = DailyCasesAndDeaths.download();

        HashMap<String, Component> hashMap = new HashMap<>();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        List<String> strings = new LinkedList<>();
        List<Component> components = new LinkedList<>();
        for (DailyCasesAndDeaths tem : dataRows) {
            if (hashMap.size() > 15) {
                break;
            }
            double temp1 = tem.getNew_cases();
            if (!hashMap.containsKey(tem.getCountry())) {
                Component component = new Component(tem.getCountry(), new LinkedList<Object>());
                component.getData().add(temp1);
                hashMap.put(tem.getCountry(), component);
                strings.add(component.getName());
                components.add(component);
            } else {
                if (hashMap.get(tem.getCountry()).getData().size() <= 30) {
                    hashMap.get(tem.getCountry()).getData().add(temp1);
                }
            }
        }
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("data", strings);
        stringObjectHashMap.put("name", temp);
        stringObjectHashMap.put("type", "line");
        stringObjectHashMap.put("data", components);
        return new ResponseEntity<>(stringObjectHashMap, HttpStatus.OK);
    }

    public ResponseEntity<?> getLineInformation2() throws IOException {
        ArrayList<LatestCasesAndDeaths> dataRows = LatestCasesAndDeaths.download();
        HashMap<String, Component> hashMap = new HashMap<>();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        List<String> strings = new LinkedList<>();
        List<Component> components = new LinkedList<>();
        for (LatestCasesAndDeaths tem : dataRows) {
            if (hashMap.size() > 15) {
                break;
            }
            double temp1 = tem.getCasesNewlyIn7days();
            if (!hashMap.containsKey(tem.getName())) {
                Component component = new Component(tem.getName(), new LinkedList<Object>());
                component.getData().add(temp1);
                hashMap.put(tem.getName(), component);
                strings.add(component.getName());
                components.add(component);
            } else {
                if (hashMap.get(tem.getName()).getData().size() <= 30) {
                    hashMap.get(tem.getName()).getData().add(temp1);
                }
            }
        }
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("data", strings);
        stringObjectHashMap.put("name", temp);
        stringObjectHashMap.put("type", "line");
        stringObjectHashMap.put("data", components);
        return new ResponseEntity<>(stringObjectHashMap, HttpStatus.OK);
    }

    public ResponseEntity<?> getLineInformation3() throws IOException {
        ArrayList<VaccinationData> dataRows = VaccinationData.download();

        HashMap<String, Component> hashMap = new HashMap<>();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        List<String> strings = new LinkedList<>();
        List<Component> components = new LinkedList<>();
        for (VaccinationData tem : dataRows) {
            if (hashMap.size() > 15) {
                break;
            }
            double temp1 = tem.getPERSONS_FULLY_VACCINATED();
            if (!hashMap.containsKey(tem.getCOUNTRY())) {
                Component component = new Component(tem.getCOUNTRY(), new LinkedList<Object>());
                component.getData().add(temp1);
                hashMap.put(tem.getCOUNTRY(), component);
                strings.add(component.getName());
                components.add(component);
            } else {
                if (hashMap.get(tem.getCOUNTRY()).getData().size() <= 30) {
                    hashMap.get(tem.getCOUNTRY()).getData().add(temp1);
                }
            }
        }
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("data", strings);
        stringObjectHashMap.put("name", temp);
        stringObjectHashMap.put("type", "line");
        stringObjectHashMap.put("data", components);
        return new ResponseEntity<>(stringObjectHashMap, HttpStatus.OK);
    }
    public ResponseEntity<?> redownload() throws Exception{
        dataRows = DailyCasesAndDeaths.download();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<?> getInformations(int page) throws Exception{
        HashMap<String, Object> temp = new HashMap<>();
        List<Component> components = new LinkedList<>();
        for (int i=Math.min((page-1)*10,dataRows.size());i<Math.min(page*10,dataRows.size());++i){
            DailyCasesAndDeaths deaths=dataRows.get(i);
            Component component = new Component(deaths.getCountry(), new LinkedList<Object>());
            component.setData(getValue(DailyCasesAndDeaths.class,deaths));
            components.add(component);
        }
        temp.put("table",components);
        temp.put("length",dataRows.size());
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    public ResponseEntity<?> searchInformations(String sort,String search) throws Exception{
        Field f=DailyCasesAndDeaths.class.getDeclaredField(sort);
        f.setAccessible(true);
        if (search.equals("")){
            dataRows.clear();
            dataRows.addAll(storedData);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        dataRows= (ArrayList<DailyCasesAndDeaths>) dataRows.stream().filter(new Predicate<DailyCasesAndDeaths>() {
            @SneakyThrows
            @Override
            public boolean test(DailyCasesAndDeaths dailyCasesAndDeaths) {
                return String.valueOf(f.get(dailyCasesAndDeaths)).contains(search);
            }
        }).collect(Collectors.toList());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<?> sortInformations(String sort) throws Exception{
        Field f=DailyCasesAndDeaths.class.getDeclaredField(sort);
        f.setAccessible(true);
        if (f.getType()==int.class) {
            dataRows.sort(new Comparator<DailyCasesAndDeaths>() {
                @SneakyThrows
                @Override
                public int compare(DailyCasesAndDeaths o1, DailyCasesAndDeaths o2) {
                    int temp1=(int)f.get(o1);
                    int temp2=(int)f.get(o2);
                    if (temp1<temp2){
                        return 1;
                    }else if (temp1>temp2){
                        return -1;
                    }return 0;
                }
            });
        }else if(f.getType()==String.class){
            dataRows.sort(new Comparator<DailyCasesAndDeaths>() {
                @SneakyThrows
                @Override
                public int compare(DailyCasesAndDeaths o1, DailyCasesAndDeaths o2) {
                    String temp1=String.valueOf(f.get(o1));
                    String temp2=String.valueOf(f.get(o2));
                   return temp1.compareTo(temp2);
                }
            });
        }else if (f.getType()==double.class) {
            dataRows.sort(new Comparator<DailyCasesAndDeaths>() {
                @SneakyThrows
                @Override
                public int compare(DailyCasesAndDeaths o1, DailyCasesAndDeaths o2) {
                    double temp1=(double)f.get(o1);
                    double temp2=(double)f.get(o2);
                    if (temp1<temp2){
                        return 1;
                    }else if (temp1>temp2){
                        return -1;
                    }return 0;
                }
            });
        }else {
            dataRows.sort(new Comparator<DailyCasesAndDeaths>() {
                @SneakyThrows
                @Override
                public int compare(DailyCasesAndDeaths o1, DailyCasesAndDeaths o2) {
                    double temp1=(double)f.get(o1);
                    double temp2=(double)f.get(o2);
                    if (temp1<temp2){
                        return 1;
                    }else if (temp1>temp2){
                        return -1;
                    }return 0;
                }
            });
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public static List<Object> getValue(Class<?>clazz, Object deaths ) throws IllegalAccessException {
        List<Object> object=new ArrayList<>();
        for (Field field:clazz.getDeclaredFields()){
            field.setAccessible(true);
           object.add(field.get(deaths));
        }return object;
    }

    public ResponseEntity<?> getLineInformation4() throws IOException {
        ArrayList<VaccinationMetadata> dataRows = VaccinationMetadata.download();
//
//        HashMap<String, Component> hashMap = new HashMap<>();
//        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
//        List<String> strings = new LinkedList<>();
//        List<Component> components = new LinkedList<>();
//        for (VaccinationMetadata tem : dataRows) {
//            if (hashMap.size() > 15) {
//                break;
//            }
//            double temp1 = tem.getNew_cases();
//            if (!hashMap.containsKey(tem.getCountry())) {
//                Component component = new Component(tem.getCountry(), new LinkedList<Object>());
//                component.getData().add(temp1);
//                hashMap.put(tem.getCountry(), component);
//                strings.add(component.getName());
//                components.add(component);
//            } else {
//                if (hashMap.get(tem.getCountry()).getData().size() <= 30) {
//                    hashMap.get(tem.getCountry()).getData().add(temp1);
//                }
//            }
//        }
//        HashMap<String, Object> temp = new HashMap<>();
//        temp.put("data", strings);
//        stringObjectHashMap.put("name", temp);
//        stringObjectHashMap.put("type", "line");
//        stringObjectHashMap.put("data", components);
//        return new ResponseEntity<>(stringObjectHashMap, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

//    public ResponseEntity<?> getBarInformation() {
//        HashMap<String, Component> hashMap = new HashMap<>();
//        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
//        List<String> strings = new LinkedList<>();
//        List<Component> components = new LinkedList<>();
//        for (DataRow dataRow : dataRows) {
//            if (hashMap.size() > 15) {
//                break;
//            }
//            double temp1 = dataRow.getNew_cases();
//            if (!hashMap.containsKey(dataRow.getLocation())) {
//                Component component = new Component(dataRow.getLocation(), new LinkedList<Double>());
//                component.getData().add(temp1);
//                component.setType("bar");
//                hashMap.put(dataRow.getLocation(), component);
//                strings.add(component.getName());
//                components.add(component);
//            } else {
//                if (hashMap.get(dataRow.getLocation()).getData().size() <= 30) {
//                    hashMap.get(dataRow.getLocation()).getData().add(temp1);
//                }
//            }
//        }
//        HashMap<String, Object> temp = new HashMap<>();
//        temp.put("data", strings);
//        stringObjectHashMap.put("name", temp);
//        stringObjectHashMap.put("type", "bar");
//        stringObjectHashMap.put("data", components);
//        return new ResponseEntity<>(stringObjectHashMap, HttpStatus.OK);
//    }
}
