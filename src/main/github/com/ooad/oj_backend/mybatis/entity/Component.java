package com.ooad.oj_backend.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Component implements Serializable {
    String name;
    String type="line";
    String stack="总量";
    List<Double> data;
    public Component(String name,List<Double>data){
        this.name=name;
        this.data=data;
    }
}
