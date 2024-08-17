package com.std.cov.coverages.controller;

import com.std.cov.coverages.model.dto.CoverageDto;
import com.std.cov.coverages.model.dto.CoverageResponseDto;
import com.std.cov.coverages.model.dto.CoverageUpdateDto;
import com.std.cov.coverages.model.entity.Coverage;
import com.std.cov.coverages.service.ICoverage;
import com.std.cov.utils.CoverageException;
import com.std.cov.utils.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoverageController {

    @Autowired
    private ICoverage coverageService;

    @GetMapping("/coverages")
    public ResponseEntity<?> showAll() {
        List<Coverage> getList = coverageService.listAll();
        if (getList == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("Coverages not registers")
                            .data(null)
                            .build()
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("Coverages list successful")
                        .data(getList)
                        .build()
                , HttpStatus.OK);
    }

    @PostMapping("/coverage")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody CoverageDto coverageDto) {
        try {
            Coverage coverageSave = null;
            coverageSave = coverageService.save(coverageDto);
            CoverageDto coverageResponse = CoverageResponseDto.coverageResponse(coverageSave);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Coverage create successful")
                    .data(coverageResponse)
                    .build()
                    , HttpStatus.CREATED);
        } catch (CoverageException ex) {
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

    @PutMapping("/coverage/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody CoverageUpdateDto coverageDto, @PathVariable Integer id) {
        try {
            Coverage coverageUpdate = null;
            if (coverageService.existsCoverageId(id)){
                coverageDto.setId(id);
                coverageUpdate = coverageService.update(coverageDto);
                CoverageDto coverageResponse = CoverageResponseDto.coverageResponse(coverageUpdate);
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("Coverage update successful")
                        .data(coverageResponse)
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MessageResponse.builder()
                                .message("Coverage update not exists")
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

    @DeleteMapping("/coverage/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Coverage coverageDelete = coverageService.findById(id);
            coverageService.delete(coverageDelete);
            return new ResponseEntity<>(coverageDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(exDt.getMessage())
                            .data(null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/coverage/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Coverage coverage = coverageService.findById(id);
        if (coverage == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("Coverage search not exists!")
                            .data(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        CoverageDto coverageResponse = CoverageResponseDto.coverageResponse(coverage);
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("Coverage search successful")
                        .data(coverageResponse)
                        .build()
                , HttpStatus.OK);
    }
}
