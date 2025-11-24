package lab9.mapper;

import lab9.dto.CountryDto;
import lab9.entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDto toDto(Country entity);
    Country toEntity(CountryDto dto);
}