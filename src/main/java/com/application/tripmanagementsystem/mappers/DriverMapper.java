package com.application.tripmanagementsystem.mappers;

import com.application.tripmanagementsystem.dtos.DriverDto;
import com.application.tripmanagementsystem.entities.DriverEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper implements Mapper<DriverEntity, DriverDto> {
    private final ModelMapper modelMapper;

    public DriverMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DriverDto mapTo(DriverEntity driverEntity) {
        return modelMapper.map(driverEntity,DriverDto.class);
    }

    @Override
    public DriverEntity mapFrom(DriverDto driverDto) {
        return modelMapper.map(driverDto,DriverEntity.class);
    }
}
