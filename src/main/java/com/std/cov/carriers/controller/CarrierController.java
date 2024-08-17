package com.std.cov.carriers.controller;

import com.std.cov.carriers.model.dto.CarrierDto;
import com.std.cov.carriers.model.dto.CarrierResponseDto;
import com.std.cov.carriers.model.dto.CarrierUpdateDto;
import com.std.cov.carriers.model.entity.Carrier;
import com.std.cov.carriers.service.ICarrier;
import com.std.cov.utils.CarrierException;
import com.std.cov.utils.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarrierController {

    @Autowired
    private ICarrier carrierService;

    @GetMapping("/carriers")
    public ResponseEntity<?> showAll() {
        List<Carrier> getList = carrierService.listAll();
        if (getList == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("Carriers not registers")
                            .data(null)
                            .build()
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("Carriers list successful")
                        .data(getList)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("/carrier")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody CarrierDto carrierDto) {
        try {
            Carrier carrierSave = null;
            carrierSave = carrierService.save(carrierDto);
            CarrierDto carrierResponse = CarrierResponseDto.carrierResponse(carrierSave);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Carrier create successful")
                    .data(carrierResponse)
                    .build()
                    , HttpStatus.CREATED);
        } catch (CarrierException ex) {
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

    @PutMapping("/carrier/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody CarrierUpdateDto carrierDto, @PathVariable Integer id) {
        try {
            Carrier carrierUpdate = null;
            if (carrierService.existsCarrierId(id)){
                carrierDto.setId(id);
                carrierUpdate = carrierService.update(carrierDto);
                CarrierDto carrierResponse = CarrierResponseDto.carrierResponse(carrierUpdate);
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("Carrier update successful")
                        .data(carrierResponse)
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MessageResponse.builder()
                                .message("Carrier update not exists")
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

    @DeleteMapping("/carrier/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Carrier carrierDelete = carrierService.findById(id);
            carrierService.delete(carrierDelete);
            return new ResponseEntity<>(carrierDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(exDt.getMessage())
                            .data(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/carrier/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Carrier carrier = carrierService.findById(id);
        if (carrier == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("Carrier search not exists!")
                            .data(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        CarrierDto carrierResponse = CarrierResponseDto.carrierResponse(carrier);
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("Carrier search successful")
                        .data(carrierResponse)
                        .build()
                , HttpStatus.OK);
    }
}
