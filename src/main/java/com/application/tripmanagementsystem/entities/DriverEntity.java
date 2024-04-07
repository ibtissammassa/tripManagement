package com.application.tripmanagementsystem.entities;

import com.application.tripmanagementsystem.dtos.DriversLicenceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "drivers")
public class DriverEntity {
    @Id
    private Long registrationNum;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String cin;
    private Long driversLicenceNum;
    private Date driversLicenceDeliveryDate;
    @Enumerated(EnumType.STRING)
    private DriversLicenceType driversLicenceType;
}
