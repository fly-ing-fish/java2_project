package backend.service;

import backend.mybatis.entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class ChartService {
//    List<DataRow> dataRows = Prepare.prepareData();

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

    public ResponseEntity<?> getLineInformation4() throws Exception {
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
