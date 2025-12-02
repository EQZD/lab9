package lab9.service;

import lab9.dto.CountryDto;
import lab9.entity.Country;
import lab9.mapper.CountryMapper;
import lab9.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repo;
    private final CountryMapper mapper;

    public CountryServiceImpl(CountryRepository repo, CountryMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<CountryDto> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CountryDto get(Long id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    @Override
    public CountryDto create(CountryDto dto) {
        Country saved = repo.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public CountryDto update(Long id, CountryDto dto) {
        return repo.findById(id)
                .map(ex -> {
                    ex.setName(dto.getName());
                    ex.setCode(dto.getCode());
                    Country updated = repo.save(ex);
                    return mapper.toDto(updated);
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
