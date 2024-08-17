package com.std.cov.carrier_services.controller;

import com.std.cov.carrier_services.model.dto.CarrierServiceDto;
import com.std.cov.carrier_services.model.dto.CarrierServiceResponseDto;
import com.std.cov.carrier_services.model.dto.CarrierServiceUpdateDto;
import com.std.cov.carrier_services.model.entity.CarrierService;
import com.std.cov.carrier_services.service.ICarrierService;
import com.std.cov.utils.CarrierServiceException;
import com.std.cov.utils.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarrierServiceController {

    @Autowired
    private ICarrierService carrierServiceService;

    @GetMapping("/carrier-services")
    public ResponseEntity<?> showAll() {
        List<CarrierService> getList = carrierServiceService.listAll();
        if (getList == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("CarrierServices not registers")
                            .data(null)
                            .build()
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("CarrierServices list successful")
                        .data(getList)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("/carrier-services")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody CarrierServiceDto carrierServiceDto) {
        try {
            CarrierService carrierServiceSave = null;
            carrierServiceSave = carrierServiceService.save(carrierServiceDto);
            CarrierServiceDto carrierServiceResponse = CarrierServiceResponseDto.carrierServiceResponse(carrierServiceSave);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("CarrierService create successful")
                    .data(carrierServiceResponse)
                    .build()
                    , HttpStatus.CREATED);
        } catch (CarrierServiceException ex) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(ex.getMessage())
                            .data(null)
                            .build(),
                    HttpStatus.CONFLICT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(exDt.getMessage())
                            .data(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/carrier-services/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody CarrierServiceUpdateDto carrierServiceDto, @PathVariable Integer id) {
        try {
            CarrierService carrierServiceUpdate = null;
            if (carrierServiceService.existsCarrierServiceId(id)){
                carrierServiceDto.setId(id);
                carrierServiceUpdate = carrierServiceService.update(carrierServiceDto);
                CarrierServiceDto carrierServiceResponse = CarrierServiceResponseDto.carrierServiceResponse(carrierServiceUpdate);
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("CarrierService update successful")
                        .data(carrierServiceResponse)
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MessageResponse.builder()
                                .message("CarrierService update not exists")
                                .data(null)
                        , HttpStatus.NOT_FOUND
                );
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(exDt.getMessage())
                            .data(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/carrier-services/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            CarrierService carrierServiceDelete = carrierServiceService.findById(id);
            carrierServiceService.delete(carrierServiceDelete);
            return new ResponseEntity<>(carrierServiceDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(exDt.getMessage())
                            .data(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/carrier-services/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        CarrierService carrierService = carrierServiceService.findById(id);
        if (carrierService == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("CarrierService search not exists!")
                            .data(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        CarrierServiceDto carrierServiceResponse = CarrierServiceResponseDto.carrierServiceResponse(carrierService);
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("CarrierService search successful")
                        .data(carrierServiceResponse)
                        .build()
                , HttpStatus.OK);
    }
}
