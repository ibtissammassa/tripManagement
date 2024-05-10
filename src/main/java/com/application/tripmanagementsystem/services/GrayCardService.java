package com.application.tripmanagementsystem.services;

import com.application.tripmanagementsystem.entities.GrayCardEntity;

public interface GrayCardService {
    boolean existsById(String registrationNumber);
    GrayCardEntity createGrayCard(GrayCardEntity grayCardEntity,String registrationNumber);
}
