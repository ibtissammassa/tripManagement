package com.application.tripmanagementsystem.services;

import com.application.tripmanagementsystem.entities.TripEntity;

import java.util.List;
import java.util.Optional;

public interface TripService {
    TripEntity createTrip(TripEntity trip);
    TripEntity updateTrip(Long id, TripEntity trip);
    Optional<TripEntity> findTrip(Long id);
    List<TripEntity> findAllTrips();
    void removeTrip(Long id);
}
