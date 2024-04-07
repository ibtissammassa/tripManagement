package com.application.tripmanagementsystem.entities;

import com.application.tripmanagementsystem.dtos.DriversLicenceType;
import com.application.tripmanagementsystem.dtos.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicles")
public class VehicleEntity {
    @Id
    private Long id;
    private String brand;
    private String model;
    private VehicleType vehicleType;
    private Long mileage;
    private Boolean available;
    private DriversLicenceType requiredLicenceType;
    private String specialEquipments;
    @OneToOne
    @JoinColumn(name = "registrationNumber")
    private GrayCardEntity grayCard;
}
