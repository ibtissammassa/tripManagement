package com.application.tripmanagementsystem.dtos;

import com.application.tripmanagementsystem.entities.DriverEntity;
import com.application.tripmanagementsystem.entities.VehicleEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripDto {
    private Long id;
    private Date departureDate;
    private Date arrivalDate;
    private String departure;
    private String destination;
    private String requiredVehicleType;
    private DriverEntity driver;
    private VehicleEntity vehicle;
    private Integer passengersNum;
    private String otherDetails;
}
