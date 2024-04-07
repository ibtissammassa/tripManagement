package com.application.tripmanagementsystem.repositories;

import com.application.tripmanagementsystem.entities.DriverEntity;
import org.springframework.data.repository.CrudRepository;


public interface DriverRepository extends CrudRepository<DriverEntity,Long> {
}
