package com.application.tripmanagementsystem.repositories;

import com.application.tripmanagementsystem.entities.TripEntity;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<TripEntity,Long> {
}
