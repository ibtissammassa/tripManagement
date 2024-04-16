package com.application.tripmanagementsystem.mappers;

import com.application.tripmanagementsystem.dtos.VehicleDto;
import com.application.tripmanagementsystem.entities.VehicleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper implements Mapper<VehicleEntity, VehicleDto> {
    private final ModelMapper modelMapper;

    public VehicleMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public VehicleDto mapTo(VehicleEntity vehicleEntity) {
        return this.modelMapper.map(vehicleEntity,VehicleDto.class);
    }

    @Override
    public VehicleEntity mapFrom(VehicleDto vehicleDto) {
        return this.modelMapper.map(vehicleDto,VehicleEntity.class);
    }
}
