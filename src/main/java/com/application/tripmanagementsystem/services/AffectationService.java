package com.application.tripmanagementsystem.services;

import com.application.tripmanagementsystem.dtos.DriverDto;
import com.application.tripmanagementsystem.dtos.TripDto;
import com.application.tripmanagementsystem.dtos.VehicleDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AffectationService {
    public List<DriverDto> getAvailableDrivers(Date departure, Date arrival);
    public List<VehicleDto> getAvailableVehicles(Date departure, Date arrival);
    public Optional<TripDto> affectToTrip(Long tripId, Long driverRegistrationNum, Long vehicleId);
}
