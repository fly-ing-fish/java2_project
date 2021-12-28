package backend.service;

import backend.mybatis.entity.*;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ChartService {
    //    List<DataRow> dataRows = Prepare.prepareData();
    static ArrayList<DailyCasesAndDeaths> dataRows;
    static ArrayList<DailyCasesAndDeaths>storedData=new ArrayList<>();
    static ArrayList<LatestCasesAndDeaths> LatestDataRows;
    static ArrayList<LatestCasesAndDeaths>storedLatestData=new ArrayList<>();
    static ArrayList<VaccinationData> vaccinationData;
    static ArrayList<VaccinationData>storedVaccinationData=new ArrayList<>();
    static ArrayList<VaccinationMetadata> vaccinationMetadata;
    static ArrayList<VaccinationMetadata>storedVaccinationMetadata=new ArrayList<>();
    static List<DataRow>dataRows1=Prepare.prepareData();
    static List<DataRow>dataRows2=Prepare.prepareData();
    static {
        try {
            dataRows = DailyCasesAndDeaths.download();
            storedData.addAll(dataRows);
            LatestDataRows = LatestCasesAndDeaths.download();
            storedLatestData.addAll(LatestDataRows);
            vaccinationData = VaccinationData.download();
            storedVaccinationData.addAll(vaccinationData);
            vaccinationMetadata = VaccinationMetadata.download();
            storedVaccinationMetadata.addAll(vaccinationMetadata);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResponseEntity<?> getLineInformation() throws IOException {
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
    public ResponseEntity<?> getLineInformation1() throws IOException {
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

    public ResponseEntity<?> getLineInformation3() throws Exception {
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
    public ResponseEntity<?> getInformations(int type,int page) throws Exception{
        HashMap<String, Object> temp = new HashMap<>();
        List<Component> components = new LinkedList<>();
        int length=0;
        if(type==1) {
            length=dataRows.size();
        }else if(type==2) {
            length=LatestDataRows.size();
        }else if(type==3) {
            length=vaccinationData.size();
        }else if(type==4) {
            length=vaccinationMetadata.size();
        }
        for (int i=Math.min((page-1)*10,length);i<Math.min(page*10,length);++i){
            Object deaths = null;
            if(type==1) {
                deaths=dataRows.get(i);
            }else if(type==2) {
                deaths=LatestDataRows.get(i);
            }else if(type==3) {
                deaths=vaccinationData.get(i);
            }else if(type==4) {
               deaths=vaccinationMetadata.get(i);
            }
            Component component = new Component("", new LinkedList<Object>());
            if(type==1) {
                component.setData(getValue(DailyCasesAndDeaths.class, deaths));
            }else if(type==2) {
                component.setData(getValue(LatestCasesAndDeaths.class, deaths));
            }else if(type==3) {
                component.setData(getValue(VaccinationData.class, deaths));
            }else if(type==4) {
                component.setData(getValue(VaccinationMetadata.class, deaths));
            }
            components.add(component);
        }
        temp.put("table",components);
        temp.put("length",length);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    public ResponseEntity<?> searchLatestInformations(String sort,String search) throws Exception{
        Field f=LatestCasesAndDeaths.class.getDeclaredField(sort);
        f.setAccessible(true);
        if (search.equals("")){
            LatestDataRows.clear();
            LatestDataRows.addAll(storedLatestData);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        LatestDataRows= (ArrayList<LatestCasesAndDeaths>) LatestDataRows.stream().filter(new Predicate<LatestCasesAndDeaths>() {
            @SneakyThrows
            @Override
            public boolean test(LatestCasesAndDeaths dailyCasesAndDeaths) {
                return String.valueOf(f.get(dailyCasesAndDeaths)).contains(search);
            }
        }).collect(Collectors.toList());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<?> sortLatestInformations(String sort) throws Exception{
        Field f=LatestCasesAndDeaths.class.getDeclaredField(sort);
        f.setAccessible(true);
        if (f.getType()==int.class) {
            LatestDataRows.sort(new Comparator<>() {
                @SneakyThrows
                @Override
                public int compare(LatestCasesAndDeaths o1, LatestCasesAndDeaths o2) {
                    int temp1 = (int) f.get(o1);
                    int temp2 = (int) f.get(o2);
                    if (temp1 < temp2) {
                        return 1;
                    } else if (temp1 > temp2) {
                        return -1;
                    }
                    return 0;
                }
            });
        }else if(f.getType()==String.class){
            LatestDataRows.sort(new Comparator<>() {
                @SneakyThrows
                @Override
                public int compare(LatestCasesAndDeaths o1, LatestCasesAndDeaths o2) {
                    String temp1=String.valueOf(f.get(o1));
                    String temp2=String.valueOf(f.get(o2));
                    return temp1.compareTo(temp2);
                }
            });
        }else if (f.getType()==double.class) {
            LatestDataRows.sort(new Comparator<LatestCasesAndDeaths>() {
                @SneakyThrows
                @Override
                public int compare(LatestCasesAndDeaths o1, LatestCasesAndDeaths o2) {
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
            LatestDataRows.sort(new Comparator<LatestCasesAndDeaths>() {
                @SneakyThrows
                @Override
                public int compare(LatestCasesAndDeaths o1, LatestCasesAndDeaths o2) {
                    LocalDate temp1=(LocalDate) f.get(o1);
                    LocalDate temp2=(LocalDate) f.get(o2);
                    if (temp1.isAfter(temp2)){
                        return -1;
                    }else if (temp1.isBefore(temp2)){
                        return 1;
                    }return 0;
                }
            });
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<?> searchVaccinationInformations(String sort,String search) throws Exception{
        Field f=VaccinationData.class.getDeclaredField(sort);
        f.setAccessible(true);
        if (search.equals("")){
            vaccinationData.clear();
            vaccinationData.addAll(storedVaccinationData);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        vaccinationData= (ArrayList<VaccinationData>) vaccinationData.stream().filter(new Predicate<VaccinationData>() {
            @SneakyThrows
            @Override
            public boolean test(VaccinationData dailyCasesAndDeaths) {
                return String.valueOf(f.get(dailyCasesAndDeaths)).contains(search);
            }
        }).collect(Collectors.toList());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<?> sortVaccinationInformation(String sort) throws Exception{
        Field f=VaccinationData.class.getDeclaredField(sort);
        f.setAccessible(true);
        if (f.getType()==int.class) {
            vaccinationData.sort(new Comparator<>() {
                @SneakyThrows
                @Override
                public int compare(VaccinationData o1, VaccinationData o2) {
                    int temp1 = (int) f.get(o1);
                    int temp2 = (int) f.get(o2);
                    if (temp1 < temp2) {
                        return 1;
                    } else if (temp1 > temp2) {
                        return -1;
                    }
                    return 0;
                }
            });
        }else if(f.getType()==String.class){
            vaccinationData.sort(new Comparator<VaccinationData>() {
                @SneakyThrows
                @Override
                public int compare(VaccinationData o1, VaccinationData o2) {
                    String temp1=String.valueOf(f.get(o1));
                    String temp2=String.valueOf(f.get(o2));
                    return temp1.compareTo(temp2);
                }
            });
        }else if (f.getType()==double.class) {
            vaccinationData.sort(new Comparator<VaccinationData>() {
                @SneakyThrows
                @Override
                public int compare(VaccinationData o1, VaccinationData o2) {
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
            vaccinationData.sort(new Comparator<VaccinationData>() {
                @SneakyThrows
                @Override
                public int compare(VaccinationData o1, VaccinationData o2) {
                    LocalDate temp1=(LocalDate) f.get(o1);
                    LocalDate temp2=(LocalDate) f.get(o2);
                    if (temp1.isAfter(temp2)){
                        return -1;
                    }else if (temp1.isBefore(temp2)){
                        return 1;
                    }return 0;
                }
            });
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<?> searchVaccinationMetaInformations(String sort,String search) throws Exception{
        Field f=VaccinationMetadata.class.getDeclaredField(sort);
        f.setAccessible(true);
        if (search.equals("")){
            vaccinationMetadata.clear();
            vaccinationMetadata.addAll(storedVaccinationMetadata);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        vaccinationMetadata= (ArrayList<VaccinationMetadata>) vaccinationMetadata.stream().filter(new Predicate<VaccinationMetadata>() {
            @SneakyThrows
            @Override
            public boolean test(VaccinationMetadata dailyCasesAndDeaths) {
                return String.valueOf(f.get(dailyCasesAndDeaths)).contains(search);
            }
        }).collect(Collectors.toList());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<?> sortVaccinationMetaInformation(String sort) throws Exception{
        Field f=VaccinationMetadata.class.getDeclaredField(sort);
        f.setAccessible(true);
        if (f.getType()==int.class) {
            vaccinationMetadata.sort(new Comparator<>() {
                @SneakyThrows
                @Override
                public int compare(VaccinationMetadata o1, VaccinationMetadata o2) {
                    int temp1 = (int) f.get(o1);
                    int temp2 = (int) f.get(o2);
                    if (temp1 < temp2) {
                        return 1;
                    } else if (temp1 > temp2) {
                        return -1;
                    }
                    return 0;
                }
            });
        }else if(f.getType()==String.class){
            vaccinationMetadata.sort(new Comparator<VaccinationMetadata>() {
                @SneakyThrows
                @Override
                public int compare(VaccinationMetadata o1, VaccinationMetadata o2) {
                    String temp1=String.valueOf(f.get(o1));
                    String temp2=String.valueOf(f.get(o2));
                    return temp1.compareTo(temp2);
                }
            });
        }else if (f.getType()==double.class) {
            vaccinationMetadata.sort(new Comparator<VaccinationMetadata>() {
                @SneakyThrows
                @Override
                public int compare(VaccinationMetadata o1, VaccinationMetadata o2) {
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
            vaccinationMetadata.sort(new Comparator<VaccinationMetadata>() {
                @SneakyThrows
                @Override
                public int compare(VaccinationMetadata o1, VaccinationMetadata o2) {
                    LocalDate temp1=(LocalDate) f.get(o1);
                    LocalDate temp2=(LocalDate) f.get(o2);
                    if (temp1.isAfter(temp2)){
                        return -1;
                    }else if (temp1.isBefore(temp2)){
                        return 1;
                    }return 0;
                }
            });
        }
        return new ResponseEntity<>(HttpStatus.OK);
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
            dataRows.sort(new Comparator<>() {
                @SneakyThrows
                @Override
                public int compare(DailyCasesAndDeaths o1, DailyCasesAndDeaths o2) {
                    int temp1 = (int) f.get(o1);
                    int temp2 = (int) f.get(o2);
                    if (temp1 < temp2) {
                        return 1;
                    } else if (temp1 > temp2) {
                        return -1;
                    }
                    return 0;
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
                    LocalDate temp1=(LocalDate) f.get(o1);
                    LocalDate temp2=(LocalDate) f.get(o2);
                    if (temp1.isAfter(temp2)){
                        return -1;
                    }else if (temp1.isBefore(temp2)){
                        return 1;
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

    public ResponseEntity<?> getLineInformation4() throws Exception {
        ArrayList<VaccinationMetadata> dataRows = VaccinationMetadata.download();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    public ResponseEntity<?> getBarInformation() throws IOException {
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
                component.setType("bar");
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
        stringObjectHashMap.put("type", "bar");
        stringObjectHashMap.put("data", components);
        return new ResponseEntity<>(stringObjectHashMap, HttpStatus.OK);
    }
    public ResponseEntity<?> getPieInformation() throws IOException {
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
                component.setType("bar");
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
        stringObjectHashMap.put("type", "bar");
        stringObjectHashMap.put("data", components);
        return new ResponseEntity<>(stringObjectHashMap, HttpStatus.OK);
    }
    public ResponseEntity<?> updateGiuHub() throws Exception {
        System.out.println();
        System.out.println("read data from gitHub");
        readDataFromGithub.download();
        System.out.println("success");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ResponseEntity<?> updateWho() throws Exception {
        System.out.println();
        System.out.println("read data from Who");
        readDataFromWho.download();
        System.out.println("success");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
