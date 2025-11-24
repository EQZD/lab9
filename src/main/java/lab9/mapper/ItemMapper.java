package lab9.mapper;

import lab9.dto.ItemDto;
import lab9.entity.Item;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "manufacturer.id", target = "manufacturerId")
    ItemDto toDto(Item entity);

    @InheritInverseConfiguration
    @Mapping(target = "manufacturer", ignore = true)
    Item toEntity(ItemDto dto);
}
