package com.application.tripmanagementsystem;

import com.application.tripmanagementsystem.entities.DriverEntity;

import java.sql.Date;

public class UtilData {
    public static DriverEntity createDriver1(){
        return DriverEntity.builder().registrationNum(1L).birthDay(new Date(2000,10,20)).cin("JY42825").firstName("Naoufal").lastName("jrhaider").build();
    }
    public static DriverEntity createDriver2(){
        return DriverEntity.builder().registrationNum(2L).birthDay(new Date(2000,10,20)).cin("JY42825").firstName("Naoufal").lastName("jrhaider").build();
    }
    public static DriverEntity createDriver3(){
        return DriverEntity.builder().registrationNum(3L).birthDay(new Date(2000,10,20)).cin("JY42825").firstName("Naoufal").lastName("jrhaider").build();
    }
    public static DriverEntity createDriver4(){
        return DriverEntity.builder().registrationNum(4L).birthDay(new Date(2000,10,20)).cin("JY42825").firstName("Naoufal").lastName("jrhaider").build();
    }
}
