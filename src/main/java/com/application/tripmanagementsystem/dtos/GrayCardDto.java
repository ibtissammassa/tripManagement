package com.application.tripmanagementsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrayCardDto {
    private String registrationNumber;
    private String interiorRegistration;
    private Date firstUsageDate;
    private Date firstUsageDateInMorocco;
    private String usage;
    private String ownerFullName;
    private Date experationDate;
    private String fuelType;
    private String frameNumber;
    private Integer cylinderNumber;
    private Integer fiscalPower;
    private Integer seatNumber;
    private Integer totalWeight;
    private Integer emptyWeight;
    private Integer weightWithCargo;
}
