package com.application.tripmanagementsystem.repositories;

import com.application.tripmanagementsystem.UtilData;
import com.application.tripmanagementsystem.entities.GrayCardEntity;
import com.application.tripmanagementsystem.entities.VehicleEntity;
import com.application.tripmanagementsystem.services.VehicleService;
import org.antlr.v4.runtime.misc.LogManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
public class VehicleRepositoryTest {

    private final VehicleRepository vehicleRepository;
    private final GrayCardRepository grayCardRepository;

    @Autowired
    public VehicleRepositoryTest(VehicleRepository vehicleRepository,VehicleService vehicleService,GrayCardRepository grayCardRepository){
        this.vehicleRepository = vehicleRepository;
        this.grayCardRepository = grayCardRepository;
    }

    @Test
    void testThatCreateAndReadVehicle(){
        //GrayCardEntity grayCard = UtilData.createGrayCard1();
        //GrayCardEntity savedGrayCard = grayCardRepository.save(grayCard);

        VehicleEntity vehicle = UtilData.createVehicle1();
        //vehicle.setGrayCard(savedGrayCard);
        VehicleEntity savedVehicle = vehicleRepository.save(vehicle);

        Optional<VehicleEntity> readVehicle = vehicleRepository.findById(vehicle.getId());

        assertThat(vehicle).isEqualTo(savedVehicle);
        assertThat(readVehicle).isNotEqualTo(Optional.empty());
        assertThat(vehicle).isEqualTo(readVehicle.get());
    }

    @Test
    void testThatReadMultipleVehicles(){
        //GrayCardEntity grayCard1 = UtilData.createGrayCard1();
        //GrayCardEntity savedGrayCard1 = grayCardRepository.save(grayCard1);
        VehicleEntity vehicle1 = UtilData.createVehicle1();
        //vehicle1.setGrayCard(savedGrayCard1);

        //GrayCardEntity grayCard2 = UtilData.createGrayCard2();
        //GrayCardEntity savedGrayCard2 = grayCardRepository.save(grayCard2);
        VehicleEntity vehicle2 = UtilData.createVehicle2();
        //vehicle2.setGrayCard(savedGrayCard2);

//        GrayCardEntity grayCard3 = UtilData.createGrayCard3();
//        GrayCardEntity savedGrayCard3 = grayCardRepository.save(grayCard3);
        VehicleEntity vehicle3 = UtilData.createVehicle3();
//        vehicle3.setGrayCard(savedGrayCard3);

//        GrayCardEntity grayCard4 = UtilData.createGrayCard4();
//        GrayCardEntity savedGrayCard4 = grayCardRepository.save(grayCard4);
        VehicleEntity vehicle4 = UtilData.createVehicle4();
//        vehicle4.setGrayCard(savedGrayCard4);

        List<VehicleEntity> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        vehicles.add(vehicle4);

        Iterable<VehicleEntity> savedVehicles = vehicleRepository.saveAll(vehicles);
        Iterable<VehicleEntity> readVehicles = vehicleRepository.findAll();

        assertThat(savedVehicles).containsExactly(vehicle1,vehicle2,vehicle3,vehicle4);
        assertThat(readVehicles).containsExactly(vehicle1,vehicle2,vehicle3,vehicle4);

    }

    @Test
    void testThatDeleteVehicle(){
        VehicleEntity vehicle = UtilData.createVehicle1();
        vehicleRepository.save(vehicle);
        Optional<VehicleEntity> readVehicle = vehicleRepository.findById(vehicle.getId());
        assertThat(vehicle).isEqualTo(readVehicle.get());
        vehicleRepository.delete(vehicle);
        readVehicle= vehicleRepository.findById(vehicle.getId());
        assertThat(readVehicle).isEmpty();
    }

    @Test
    void testThatDeleteVehicleById(){
        VehicleEntity vehicle = UtilData.createVehicle1();
        vehicleRepository.save(vehicle);
        Optional<VehicleEntity> readVehicle = vehicleRepository.findById(vehicle.getId());
        assertThat(vehicle).isEqualTo(readVehicle.get());

        vehicleRepository.deleteById(vehicle.getId());
        readVehicle = vehicleRepository.findById(vehicle.getId());
        assertThat(readVehicle).isEmpty();
    }

    @Test
    void testIsVehicleExist(){
        VehicleEntity vehicle = UtilData.createVehicle1();
        vehicleRepository.save(vehicle);
        boolean a = vehicleRepository.existsById(vehicle.getId());
        boolean b = vehicleRepository.existsById(55L);
        assertThat(b).isFalse();
        assertThat(a).isTrue();
    }
}
