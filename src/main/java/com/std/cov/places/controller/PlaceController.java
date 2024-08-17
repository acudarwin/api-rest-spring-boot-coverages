package com.std.cov.places.controller;

import com.std.cov.carriers.model.dto.CarrierDto;
import com.std.cov.carriers.model.dto.CarrierResponseDto;
import com.std.cov.places.model.dto.PlaceDto;
import com.std.cov.places.model.dto.PlaceResponseDto;
import com.std.cov.places.model.dto.PlaceUpdateDto;
import com.std.cov.places.model.entity.Place;
import com.std.cov.places.service.IPlace;
import com.std.cov.utils.MessageResponse;
import com.std.cov.utils.PlaceException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;

@RestController
public class PlaceController {

    @Autowired
    private IPlace placeService;

    @GetMapping("/places")
    public ResponseEntity<?> showAll() {
        List<Place> getList = placeService.listAll();
        if (getList == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("Place not registers")
                            .data(null)
                            .build()
                    , HttpStatus.OK);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("Place list successful")
                        .data(getList)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("/place")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody PlaceDto placeDto) {
        try {
            Place placeSave = null;
            placeSave = placeService.save(placeDto);
            PlaceDto placeResponse = PlaceResponseDto.placeResponse(placeSave);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Place create successful")
                    .data(placeResponse)
                    .build()
                    , HttpStatus.CREATED);
        } catch (PlaceException ex) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(ex.getMessage())
                            .data(null)
                            .build()
                    , HttpStatus.CONFLICT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(exDt.getMessage())
                            .data(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/place/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody PlaceUpdateDto placeDto, @PathVariable Integer id) {
        try {
            Place placeUpdate = null;
            if (placeService.existsPlaceId(id)) {
                placeDto.setId(id);
                placeUpdate = placeService.update(placeDto);
                PlaceDto placeResponse = PlaceResponseDto.placeResponse(placeUpdate);
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("Place update successful")
                        .data(placeResponse)
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MessageResponse.builder()
                                .message("Place update not exists")
                                .data(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(exDt.getMessage())
                    .data(null)
                    .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/place/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Place placeDelete = placeService.findById(id);
            placeService.delete(placeDelete);
            return new ResponseEntity<>(placeDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(exDt.getMessage())
                    .data(null)
                    .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/place/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Place place = placeService.findById(id);
        if (place == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("Place search not exists!")
                            .data(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        PlaceDto placeResponse = PlaceResponseDto.placeResponse(place);
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("Place search successful")
                        .data(placeResponse)
                        .build()
                , HttpStatus.OK);
    }

}
