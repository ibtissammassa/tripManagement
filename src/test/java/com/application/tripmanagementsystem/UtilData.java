package com.application.tripmanagementsystem;

import com.application.tripmanagementsystem.dtos.DriversLicenceType;
import com.application.tripmanagementsystem.dtos.VehicleType;
import com.application.tripmanagementsystem.entities.DriverEntity;
import com.application.tripmanagementsystem.entities.GrayCardEntity;
import com.application.tripmanagementsystem.entities.VehicleEntity;

import java.sql.Date;

public class UtilData {
    //Drivers
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

    //Vehicles
    public static VehicleEntity createVehicle1(){
        return VehicleEntity.builder().id(1L).brand("Toyota").model("New model").vehicleType(String.valueOf(VehicleType.CAR)).mileage(10000L).available(true).requiredLicenceType(String.valueOf(DriversLicenceType.D)).specialEquipments("Air Conditioning").build();
    }
    public static VehicleEntity createVehicle2(){
        return VehicleEntity.builder().id(2L).brand("Toyota").model("New model").vehicleType(String.valueOf(VehicleType.CAR)).mileage(10000L).available(true).requiredLicenceType(String.valueOf(DriversLicenceType.D)).specialEquipments("Air Conditioning").build();
    }
    public static VehicleEntity createVehicle3(){
        return VehicleEntity.builder().id(3L).brand("Toyota").model("New model").vehicleType(String.valueOf(VehicleType.CAR)).mileage(10000L).available(true).requiredLicenceType(String.valueOf(DriversLicenceType.D)).specialEquipments("Air Conditioning").build();
    }
    public static VehicleEntity createVehicle4(){
        return VehicleEntity.builder().id(4L).brand("Toyota").model("New model").vehicleType(String.valueOf(VehicleType.CAR)).mileage(10000L).available(true).requiredLicenceType(String.valueOf(DriversLicenceType.D)).specialEquipments("Air Conditioning").build();
    }

    //Gray Card
    public static GrayCardEntity createGrayCard1(){
        return GrayCardEntity.builder().registrationNumber("1R").build();
    }
    public static GrayCardEntity createGrayCard2(){
        return GrayCardEntity.builder().registrationNumber("2R").build();
    }
    public static GrayCardEntity createGrayCard3(){
        return GrayCardEntity.builder().registrationNumber("3R").build();
    }
    public static GrayCardEntity createGrayCard4(){
        return GrayCardEntity.builder().registrationNumber("4R").build();
    }
}
