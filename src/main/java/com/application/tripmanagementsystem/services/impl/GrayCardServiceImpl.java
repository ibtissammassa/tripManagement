package com.application.tripmanagementsystem.services.impl;

import com.application.tripmanagementsystem.entities.GrayCardEntity;
import com.application.tripmanagementsystem.repositories.GrayCardRepository;
import com.application.tripmanagementsystem.services.GrayCardService;
import org.springframework.stereotype.Service;

@Service
public class GrayCardServiceImpl implements GrayCardService {
    private final GrayCardRepository grayCardRepository;
    public GrayCardServiceImpl(GrayCardRepository grayCardRepository){
        this.grayCardRepository = grayCardRepository;
    }
    @Override
    public boolean existsById(String registrationNumber) {
        return grayCardRepository.existsById(registrationNumber);
    }

    @Override
    public GrayCardEntity createGrayCard(GrayCardEntity grayCardEntity,String registrationNumber) {
        if (grayCardEntity.getRegistrationNumber() == null || grayCardEntity.getRegistrationNumber().isEmpty()) {
            System.out.println("Registration number cannot be null or empty");
        }else{
            System.out.println("Registration number is not null");
        }
        return grayCardRepository.save(grayCardEntity);
    }
}
