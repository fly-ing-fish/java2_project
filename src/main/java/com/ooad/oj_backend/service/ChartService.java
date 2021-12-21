package com.ooad.oj_backend.service;

import com.ooad.oj_backend.mybatis.entity.DataRow;
import com.ooad.oj_backend.mybatis.entity.Prepare;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ooad.oj_backend.mybatis.entity.Component;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class ChartService {
    List<DataRow>dataRows=Prepare.prepareData();
    public ResponseEntity<?> getLineInformation() {
        HashMap<String, Component>hashMap=new HashMap<>();
        HashMap<String,Object>stringObjectHashMap=new HashMap<>();
        List<String>strings=new LinkedList<>();
        List<Component>components=new LinkedList<>();
        for(DataRow dataRow:dataRows) {
            if(hashMap.size()>15){
                break;
            }
            double temp1=dataRow.getNew_cases();
            if(!hashMap.containsKey(dataRow.getLocation())) {
                Component component=new Component(dataRow.getLocation(),new LinkedList<Double>());
                component.getData().add(temp1);
                hashMap.put(dataRow.getLocation(),component);
                strings.add(component.getName());
                components.add(component);
            }else {
                if(hashMap.get(dataRow.getLocation()).getData().size()<=30) {
                    hashMap.get(dataRow.getLocation()).getData().add(temp1);
                }
            }
        }
        HashMap<String,Object>temp=new HashMap<>();
        temp.put("data",strings);
        stringObjectHashMap.put("name",temp);
        stringObjectHashMap.put("type","line");
        stringObjectHashMap.put("data",components);
        return new ResponseEntity<>(stringObjectHashMap,HttpStatus.OK);
    }public ResponseEntity<?> getBarInformation() {
        HashMap<String, Component>hashMap=new HashMap<>();
        HashMap<String,Object>stringObjectHashMap=new HashMap<>();
        List<String>strings=new LinkedList<>();
        List<Component>components=new LinkedList<>();
        for(DataRow dataRow:dataRows) {
            if(hashMap.size()>15){
                break;
            }
            double temp1=dataRow.getNew_cases();
            if(!hashMap.containsKey(dataRow.getLocation())) {
                Component component=new Component(dataRow.getLocation(),new LinkedList<Double>());
                component.getData().add(temp1);
                component.setType("bar");
                hashMap.put(dataRow.getLocation(),component);
                strings.add(component.getName());
                components.add(component);
            }else {
                if(hashMap.get(dataRow.getLocation()).getData().size()<=30) {
                    hashMap.get(dataRow.getLocation()).getData().add(temp1);
                }
            }
        }
        HashMap<String,Object>temp=new HashMap<>();
        temp.put("data",strings);
        stringObjectHashMap.put("name",temp);
        stringObjectHashMap.put("type","bar");
        stringObjectHashMap.put("data",components);
        return new ResponseEntity<>(stringObjectHashMap,HttpStatus.OK);
    }
}
