package com.application.tripmanagementsystem.services.impl;

import com.application.tripmanagementsystem.entities.DriverEntity;
import com.application.tripmanagementsystem.repositories.DriverRepository;
import com.application.tripmanagementsystem.services.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public DriverEntity createUpdateDriver(DriverEntity driver, Long registrationNum) {
        driver.setRegistrationNum(registrationNum);
        return driverRepository.save(driver);
    }

    @Override
    public DriverEntity updateDriver(Long registrationNum, DriverEntity driverEntity) {
        driverEntity.setRegistrationNum(registrationNum);
        return driverRepository.findById(registrationNum).map(existingDriver -> {
            Optional.ofNullable(driverEntity.getCin()).ifPresent(existingDriver::setCin);
            Optional.ofNullable(driverEntity.getDriversLicenceType()).ifPresent(existingDriver::setDriversLicenceType);
            Optional.ofNullable(driverEntity.getDriversLicenceNum()).ifPresent(existingDriver::setDriversLicenceNum);
            Optional.ofNullable(driverEntity.getBirthDay()).ifPresent(existingDriver::setBirthDay);
            Optional.ofNullable(driverEntity.getFirstName()).ifPresent(existingDriver::setFirstName);
            Optional.ofNullable(driverEntity.getLastName()).ifPresent(existingDriver::setLastName);
            Optional.ofNullable(driverEntity.getDriversLicenceDeliveryDate()).ifPresent(existingDriver::setDriversLicenceDeliveryDate);
            return driverRepository.save(existingDriver);
        }).orElseThrow(() -> new RuntimeException("Driver doesn't exist"));
    }

    @Override
    public Optional<DriverEntity> findDriver(Long registrationNum) {
        return driverRepository.findById(registrationNum);
    }

    @Override
    public List<DriverEntity> findAllDrivers() {
        return StreamSupport
                .stream(driverRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public void removeDriver(Long registrationNum) {
        driverRepository.deleteById(registrationNum);
    }

    @Override
    public boolean isExist(Long registrationNum) {
        return driverRepository.existsById(registrationNum);
    }
}
