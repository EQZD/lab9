package lab9.service;

import lab9.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> findAll();

    ItemDto get(Long id);

    ItemDto create(ItemDto dto);

    ItemDto update(Long id, ItemDto dto);

    void delete(Long id);
}
