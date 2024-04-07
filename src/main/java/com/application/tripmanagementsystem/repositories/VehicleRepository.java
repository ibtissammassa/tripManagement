package com.application.tripmanagementsystem.repositories;

import com.application.tripmanagementsystem.entities.VehicleEntity;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<VehicleEntity,Long> {
}
