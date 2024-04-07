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
public class DriverDto {
    private Long registrationNum;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String cin;
    private Long driversLicenceNum;
    private Date driversLicenceDeliveryDate;
    private DriversLicenceType driversLicenceType;
}
