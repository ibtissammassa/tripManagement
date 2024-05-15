package com.application.tripmanagementsystem.repositories;

import com.application.tripmanagementsystem.entities.DriverEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface DriverRepository extends CrudRepository<DriverEntity,Long> {
    @Query("SELECT d FROM DriverEntity d " +
            "WHERE NOT EXISTS (SELECT t FROM TripEntity t " +
            "WHERE (t.departureDate BETWEEN :departure AND :arrival) " +
            "OR (t.arrivalDate BETWEEN :departure AND :arrival) " +
            "AND t.driver = d)")
    List<DriverEntity> findAvailableDrivers(@Param("departure") Date departure, @Param("arrival") Date arrival);
}
