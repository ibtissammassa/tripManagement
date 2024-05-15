package com.application.tripmanagementsystem.repositories;

import com.application.tripmanagementsystem.entities.VehicleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface VehicleRepository extends CrudRepository<VehicleEntity,Long> {
    @Query("SELECT v FROM VehicleEntity v " +
            "WHERE NOT EXISTS (SELECT t FROM TripEntity t " +
            "WHERE (t.departureDate BETWEEN :departure AND :arrival) " +
            "OR (t.arrivalDate BETWEEN :departure AND :arrival) " +
            "AND t.vehicle = v)")
    List<VehicleEntity> findAvailableVehicles(@Param("departure") Date departure, @Param("arrival") Date arrival);
}
