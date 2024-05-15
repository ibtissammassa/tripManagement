package com.application.tripmanagementsystem.mappers;

import com.application.tripmanagementsystem.dtos.TripDto;
import com.application.tripmanagementsystem.entities.TripEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TripMapper implements Mapper<TripEntity, TripDto> {
    private final ModelMapper modelMapper;

    public TripMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TripDto mapTo(TripEntity tripEntity) {
        return modelMapper.map(tripEntity, TripDto.class);
    }

    @Override
    public TripEntity mapFrom(TripDto tripDto) {
        return modelMapper.map(tripDto, TripEntity.class);
    }
}
