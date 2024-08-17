package com.std.cov.coverages.service;

import com.std.cov.coverages.model.dto.CoverageDto;
import com.std.cov.coverages.model.dto.CoverageUpdateDto;
import com.std.cov.coverages.model.entity.Coverage;

import java.util.List;

public interface ICoverage {

    List<Coverage> listAll();

    Coverage save(CoverageDto coverage);

    Coverage update(CoverageUpdateDto coverage);

    Coverage findById(Integer id);

    void delete(Coverage coverage);

    boolean existsCoverageId(Integer id);
}
