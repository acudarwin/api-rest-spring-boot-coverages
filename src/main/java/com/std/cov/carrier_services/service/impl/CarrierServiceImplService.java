package com.std.cov.carrier_services.service.impl;

import com.std.cov.carrier_services.model.dao.CarrierServiceDao;
import com.std.cov.carrier_services.model.dto.CarrierServiceDto;
import com.std.cov.carrier_services.model.dto.CarrierServiceUpdateDto;
import com.std.cov.carrier_services.model.entity.CarrierService;
import com.std.cov.carrier_services.service.ICarrierService;
import com.std.cov.carriers.model.dao.CarrierDao;
import com.std.cov.carriers.model.entity.Carrier;
import com.std.cov.utils.CarrierException;
import com.std.cov.utils.CarrierServiceException;
import com.std.cov.utils.CoverageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarrierServiceImplService implements ICarrierService {

    private final CarrierDao carrierDao;
    private final CarrierServiceDao carrierServiceDao;

    @Autowired
    public CarrierServiceImplService(CarrierDao carrierDao, CarrierServiceDao carrierServiceDao) {
        this.carrierDao = carrierDao;
        this.carrierServiceDao = carrierServiceDao;
    }

    @Override
    public List<CarrierService> listAll() {
        return (List<CarrierService>) this.carrierServiceDao.findAll();
    }

    @Transactional
    @Override
    public CarrierService save(CarrierServiceDto carrierServiceDto) {
        List<CarrierService> validateExistsService = this.carrierServiceDao.findByCodeAndCarrierId(carrierServiceDto.getCode(), carrierServiceDto.getCarrierId());
        if (validateExistsService != null && !validateExistsService.isEmpty()) {
            throw new CoverageException("CarrierService with code " + carrierServiceDto.getCode() + " and carrier_id " + carrierServiceDto.getCarrierId() + " already exists");
        } else {
            Carrier carrier = this.carrierDao.findById(carrierServiceDto.getCarrierId())
                    .orElseThrow(() -> new CarrierException("Carrier with id " + carrierServiceDto.getCarrierId() + " not found"));
            CarrierService carrierService = CarrierService.builder()
                    .id(carrierServiceDto.getId())
                    .carrier(carrier)
                    .code(carrierServiceDto.getCode())
                    .name(carrierServiceDto.getName())
                    .description(carrierServiceDto.getDescription())
                    .isNormal(carrierServiceDto.getIsNormal())
                    .isReturn(carrierServiceDto.getIsReturn())
                    .isPickup(carrierServiceDto.getIsPickup())
                    .build();
            carrierService.setCreatedAt(LocalDateTime.now());
            carrierService.setUpdatedAt(LocalDateTime.now());
            return this.carrierServiceDao.save(carrierService);
        }

    }

    @Transactional
    @Override
    public CarrierService update(CarrierServiceUpdateDto carrierServiceDto) {
        CarrierService existingCarrierService = this.carrierServiceDao.findById(carrierServiceDto.getId()).orElseThrow(() -> new RuntimeException("CarrierService Not Found"));
        Carrier carrier = carrierDao.findById(carrierServiceDto.getCarrierId())
                .orElseThrow(() -> new CarrierServiceException("Carrier Not Found"));
        CarrierService carrierService = CarrierService.builder()
                .id(carrierServiceDto.getId())
                .carrier(carrier)
                .code(carrierServiceDto.getCode())
                .name(carrierServiceDto.getName())
                .description(carrierServiceDto.getDescription())
                .isNormal(carrierServiceDto.getIsNormal())
                .isReturn(carrierServiceDto.getIsReturn())
                .isPickup(carrierServiceDto.getIsPickup())
                .createdAt(existingCarrierService.getCreatedAt())
                .updatedAt(carrierServiceDto.getUpdatedAt())
                .deletedAt(carrierServiceDto.getDeletedAt())
                .build();
        carrierService.setUpdatedAt(LocalDateTime.now());
        return this.carrierServiceDao.save(carrierService);
    }

    @Transactional(readOnly = true)
    @Override
    public CarrierService findById(Integer id) {
        return this.carrierServiceDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(CarrierService carrierService) {
        this.carrierServiceDao.delete(carrierService);
    }

    @Override
    public boolean existsCarrierServiceId(Integer id) {
        return this.carrierServiceDao.existsById(id);
    }
}
