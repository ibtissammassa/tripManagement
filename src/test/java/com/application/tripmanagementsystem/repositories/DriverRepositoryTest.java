package com.application.tripmanagementsystem.repositories;

import com.application.tripmanagementsystem.UtilData;
import com.application.tripmanagementsystem.entities.DriverEntity;
import com.application.tripmanagementsystem.services.DriverService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
public class DriverRepositoryTest {
    private final DriverRepository driverRepository;
    private final DriverService driverService;

    @Autowired
    public DriverRepositoryTest(DriverRepository driverRepository, DriverService driverService) {
        this.driverRepository = driverRepository;
        this.driverService = driverService;
    }

    @Test
    void testThatCreateAndReadDriver() {
        DriverEntity driver = UtilData.createDriver1();
        DriverEntity savedDriver = driverRepository.save(driver);
        Optional<DriverEntity> readDriver = driverRepository.findById(driver.getRegistrationNum());
        assertThat(driver).isEqualTo(savedDriver);
        assertThat(readDriver).isNotEqualTo(Optional.empty());
        assertThat(driver).isEqualTo(readDriver.get());
    }
    @Test
    void testThatReadMultipleDriversIsWorking(){
        DriverEntity driver1 = UtilData.createDriver1();
        DriverEntity driver2 = UtilData.createDriver2();
        DriverEntity driver3 = UtilData.createDriver3();
        DriverEntity driver4 = UtilData.createDriver4();
        List<DriverEntity> drivers = new ArrayList<>();
        drivers.add(driver1);
        drivers.add(driver2);
        drivers.add(driver3);
        drivers.add(driver4);
        Iterable<DriverEntity> savedDrivers = driverRepository.saveAll(drivers);
        Iterable<DriverEntity> readDrivers = driverRepository.findAll();

        assertThat(savedDrivers).containsExactly(driver1,driver2,driver3,driver4);
        assertThat(readDrivers).containsExactly(driver1,driver2,driver3,driver4);
    }
    @Test
    void testThatDeleteDriverIsWorking(){
        DriverEntity driver = UtilData.createDriver1();
        driverRepository.save(driver);
        Optional<DriverEntity> readDriver = driverRepository.findById(driver.getRegistrationNum());
        assertThat(driver).isEqualTo(readDriver.get());
        driverRepository.delete(driver);
        readDriver = driverRepository.findById(driver.getRegistrationNum());
        assertThat(readDriver).isEmpty();

    }
    @Test
    void testIsDriverExist(){
        DriverEntity driver = UtilData.createDriver1();
        driverRepository.save(driver);
        boolean a = driverService.isExist(driver.getRegistrationNum());
        boolean b = driverService.isExist(98L);
        assertThat(b).isFalse();
        assertThat(a).isTrue();
    }
}
