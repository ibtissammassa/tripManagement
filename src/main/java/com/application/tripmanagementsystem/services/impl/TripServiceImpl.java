package com.application.tripmanagementsystem.services.impl;

import com.application.tripmanagementsystem.entities.TripEntity;
import com.application.tripmanagementsystem.repositories.TripRepository;
import com.application.tripmanagementsystem.services.TripService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public TripEntity createTrip(TripEntity trip) {
        return tripRepository.save(trip);
    }

    @Override
    public TripEntity updateTrip(Long id, TripEntity tripEntity) {
        tripEntity.setId(id);
        return tripRepository.findById(id).map(existingTrip -> {
            existingTrip.setDepartureDate(tripEntity.getDepartureDate());
            existingTrip.setArrivalDate(tripEntity.getArrivalDate());
            existingTrip.setDeparture(tripEntity.getDeparture());
            existingTrip.setDestination(tripEntity.getDestination());
            existingTrip.setRequiredVehicleType(tripEntity.getRequiredVehicleType());
            existingTrip.setPassengersNum(tripEntity.getPassengersNum());
            existingTrip.setOtherDetails(tripEntity.getOtherDetails());
            return tripRepository.save(existingTrip);
        }).orElseThrow(() -> new RuntimeException("Trip doesn't exist"));
    }

    @Override
    public Optional<TripEntity> findTrip(Long id) {
        return tripRepository.findById(id);
    }

    @Override
    public List<TripEntity> findAllTrips() {
        return StreamSupport
                .stream(tripRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void removeTrip(Long id) {
        tripRepository.deleteById(id);
    }
}
