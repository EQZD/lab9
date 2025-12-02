package lab9.service;

import lab9.dto.CountryDto;

import java.util.List;

public interface CountryService {

    List<CountryDto> findAll();

    CountryDto get(Long id);

    CountryDto create(CountryDto dto);

    CountryDto update(Long id, CountryDto dto);

    void delete(Long id);
}
