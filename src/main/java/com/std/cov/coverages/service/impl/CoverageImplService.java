package com.std.cov.coverages.service.impl;

import com.std.cov.carrier_services.model.dao.CarrierServiceDao;
import com.std.cov.carrier_services.model.entity.CarrierService;
import com.std.cov.carriers.model.dao.CarrierDao;
import com.std.cov.carriers.model.entity.Carrier;
import com.std.cov.coverages.model.dao.CoverageDao;
import com.std.cov.coverages.model.dto.CoverageDto;
import com.std.cov.coverages.model.dto.CoverageUpdateDto;
import com.std.cov.coverages.model.entity.Coverage;
import com.std.cov.coverages.service.ICoverage;
import com.std.cov.places.model.dao.PlaceDao;
import com.std.cov.places.model.entity.Place;
import com.std.cov.utils.CarrierException;
import com.std.cov.utils.CarrierServiceException;
import com.std.cov.utils.CoverageException;
import com.std.cov.utils.PlaceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CoverageImplService implements ICoverage {

    private final CoverageDao coverageDao;
    private final CarrierDao carrierDao;
    private final CarrierServiceDao carrierServiceDao;
    private final PlaceDao placeDao;

    @Autowired
    public CoverageImplService(CoverageDao coverageDao, CarrierDao carrierDao, CarrierServiceDao carrierServiceDao, PlaceDao placeDao) {
        this.coverageDao = coverageDao;
        this.carrierDao = carrierDao;
        this.carrierServiceDao = carrierServiceDao;
        this.placeDao = placeDao;
    }

    @Override
    public List<Coverage> listAll() {
        return (List<Coverage>) this.coverageDao.findAll();
    }

    @Transactional
    @Override
    public Coverage save(CoverageDto coverageDto) {
        Carrier carrier = this.carrierDao.findById(coverageDto.getCarrierId())
                .orElseThrow(() -> new CoverageException("Carrier with id " + coverageDto.getCarrierId() + " not found"));

        if (coverageDto.getCarrierServiceId() != null) {
            CarrierService carrierService = this.carrierServiceDao.findByIdAndCarrierId(coverageDto.getCarrierId(), coverageDto.getCarrierServiceId());
            if(carrierService == null) { throw new CoverageException("carrier and service not exits!"); }
        }

        if (coverageDto.getPlaceIdFrom() != null) {
            Place placeIdFrom = this.placeDao.findById(coverageDto.getPlaceIdFrom())
                    .orElseThrow(() -> new CoverageException("place_id_from " + coverageDto.getPlaceIdFrom() + "not exists!"));
        }

        if (coverageDto.getPlaceIdTo() != null) {
            Place placeIdTo = this.placeDao.findById(coverageDto.getPlaceIdTo())
                    .orElseThrow(() -> new CoverageException("place_id_to " + coverageDto.getPlaceIdTo() + "not exists!"));
        }

        List<Coverage> validateExistsCoverage = this.coverageDao.findByCarrierIdAndCarrierServiceIdAndPlaceIdFromAndPlaceIdTo(coverageDto.getCarrierId(), coverageDto.getCarrierServiceId(), coverageDto.getPlaceIdFrom(), coverageDto.getPlaceIdTo());
        if (validateExistsCoverage != null && !validateExistsCoverage.isEmpty()) {
            throw new CoverageException("Coverage with carrier " + coverageDto.getCarrierId() + " and carrier_service " + coverageDto.getCarrierServiceId() + " place_id_from " + coverageDto.getPlaceIdFrom() + "and place_id_to " + coverageDto.getPlaceIdTo() + " already exists");
        } else {
            Coverage coverage = Coverage.builder()
                    .id(coverageDto.getId())
                    .carrierId(coverageDto.getCarrierId())
                    .carrierServiceId(coverageDto.getCarrierServiceId())
                    .placeIdFrom(coverageDto.getPlaceIdFrom())
                    .placeIdTo(coverageDto.getPlaceIdTo())
                    .isBlocked(coverageDto.getIsBlocked())
                    .build();
            coverage.setCreatedAt(LocalDateTime.now());
            coverage.setUpdatedAt(LocalDateTime.now());
            return this.coverageDao.save(coverage);
        }
    }

    @Transactional
    @Override
    public Coverage update(CoverageUpdateDto coverageDto) {
        Carrier carrier = this.carrierDao.findById(coverageDto.getCarrierId())
                .orElseThrow(() -> new CoverageException("Carrier with id " + coverageDto.getCarrierId() + " not found"));

        if (coverageDto.getCarrierServiceId() != null) {
            CarrierService carrierService = this.carrierServiceDao.findByIdAndCarrierId(coverageDto.getCarrierId(), coverageDto.getCarrierServiceId());
            if(carrierService == null) { throw new CoverageException("carrier and service not exits!"); }
        }

        if (coverageDto.getPlaceIdFrom() != null) {
            Place placeIdFrom = this.placeDao.findById(coverageDto.getPlaceIdFrom())
                    .orElseThrow(() -> new CoverageException("place_id_from " + coverageDto.getPlaceIdFrom() + "not exists!"));
        }

        if (coverageDto.getPlaceIdTo() != null) {
            Place placeIdTo = this.placeDao.findById(coverageDto.getPlaceIdTo())
                    .orElseThrow(() -> new CoverageException("place_id_to " + coverageDto.getPlaceIdTo() + "not exists!"));
        }

        Coverage existingCoverage = this.coverageDao.findById(coverageDto.getId()).orElseThrow(() -> new CoverageException("Coverage Not Found"));
        Coverage coverage = Coverage.builder()
                    .id(coverageDto.getId())
                    .carrierId(coverageDto.getCarrierId())
                    .carrierServiceId(coverageDto.getCarrierServiceId())
                    .placeIdFrom(coverageDto.getPlaceIdFrom())
                    .placeIdTo(coverageDto.getPlaceIdTo())
                    .isBlocked(coverageDto.getIsBlocked())
                    .createdAt(existingCoverage.getCreatedAt())
                    .updatedAt(coverageDto.getUpdatedAt())
                    .deletedAt(coverageDto.getDeletedAt())
                    .build();
            coverage.setUpdatedAt(LocalDateTime.now());
            return this.coverageDao.save(coverage);
    }

    @Override
    public Coverage findById(Integer id) {
        return this.coverageDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Coverage coverage) {
        this.coverageDao.delete(coverage);
    }

    @Override
    public boolean existsCoverageId(Integer id) {
        return this.coverageDao.existsById(id);
    }
}
