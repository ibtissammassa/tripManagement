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
public class TripDto {
    private Long id;
    private Date departureDate;
    private Date arrivalDate;
    private String departure;
    private String destination;
    private VehicleType requiredVehicleType;
    private Integer passengersNum;
    private String otherDetails;
}
