package com.application.tripmanagementsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {
    private Long id;
    private String brand;
    private String model;
    private VehicleType vehicleType;
    private Long mileage;
    private Boolean available;
    private DriversLicenceType requiredLicenceType;
    private String[] specialEquipments;
    private GrayCardDto grayCard;
}
