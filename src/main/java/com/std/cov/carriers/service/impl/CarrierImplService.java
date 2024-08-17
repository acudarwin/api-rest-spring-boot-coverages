package com.std.cov.carriers.service.impl;

import com.std.cov.carriers.model.dao.CarrierDao;
import com.std.cov.carriers.model.dto.CarrierDto;
import com.std.cov.carriers.model.dto.CarrierUpdateDto;
import com.std.cov.carriers.model.entity.Carrier;
import com.std.cov.carriers.service.ICarrier;
import com.std.cov.utils.CarrierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarrierImplService implements ICarrier {

    @Autowired
    private CarrierDao carrierDao;

    @Override
    public List<Carrier> listAll() {
        return (List) carrierDao.findAll();
    }

    @Transactional
    @Override
    public Carrier save(CarrierDto carrierDto) {
        List<Carrier> validateExistsCode = carrierDao.findByCode(carrierDto.getCode());
        if (validateExistsCode != null && !validateExistsCode.isEmpty()) {
            throw new CarrierException("Carrier with code " + carrierDto.getCode() + " already exists.");
        } else {
            Carrier carrier = Carrier.builder()
                    .id(carrierDto.getId())
                    .cancelDelivery(carrierDto.getCancelDelivery())
                    .code(carrierDto.getCode())
                    .name(carrierDto.getName())
                    .carrierManager(carrierDto.getCarrierManager())
                    .build();
            carrier.setCreatedAt(LocalDateTime.now());
            carrier.setUpdatedAt(LocalDateTime.now());
            return carrierDao.save(carrier);
        }
    }

    @Transactional
    @Override
    public Carrier update(CarrierUpdateDto carrierDto) {
        Carrier existingCarrier = carrierDao.findById(carrierDto.getId()).orElseThrow(() -> new RuntimeException("Carrier Not Found"));
        Carrier carrier = Carrier.builder()
                .id(carrierDto.getId())
                .cancelDelivery(carrierDto.getCancelDelivery())
                .code(existingCarrier.getCode())
                .name(carrierDto.getName())
                .carrierManager(carrierDto.getCarrierManager())
                .createdAt(existingCarrier.getCreatedAt())
                .updatedAt(carrierDto.getUpdatedAt())
                .deletedAt(carrierDto.getDeletedAt())
                .build();
        carrier.setUpdatedAt(LocalDateTime.now());
        return carrierDao.save(carrier);
    }


    @Transactional(readOnly = true)
    @Override
    public Carrier findById(Integer id) {
        return carrierDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Carrier carrier) {
        carrierDao.delete(carrier);
    }

    @Override
    public boolean existsCarrierId(Integer id) {
        return carrierDao.existsById(id);
    }
}
