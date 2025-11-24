package lab9.service;

import lab9.dto.CountryDto;
import lab9.entity.Country;
import lab9.mapper.CountryMapper;
import lab9.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository repo;
    private final CountryMapper mapper;

    public CountryService(CountryRepository repo, CountryMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<CountryDto> findAll() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    public CountryDto get(Long id) {
        return repo.findById(id).map(mapper::toDto).orElse(null);
    }

    public CountryDto create(CountryDto dto) {
        Country saved = repo.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    public CountryDto update(Long id, CountryDto dto) {
        return repo.findById(id).map(ex -> {
            ex.setName(dto.getName());
            ex.setCode(dto.getCode());
            return mapper.toDto(repo.save(ex));
        }).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
