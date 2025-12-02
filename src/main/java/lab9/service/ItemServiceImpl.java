package lab9.service;

import lab9.dto.ItemDto;
import lab9.entity.Country;
import lab9.entity.Item;
import lab9.mapper.ItemMapper;
import lab9.repository.CountryRepository;
import lab9.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepo;
    private final CountryRepository countryRepo;
    private final ItemMapper mapper;

    public ItemServiceImpl(ItemRepository itemRepo,
                           CountryRepository countryRepo,
                           ItemMapper mapper) {
        this.itemRepo = itemRepo;
        this.countryRepo = countryRepo;
        this.mapper = mapper;
    }

    @Override
    public List<ItemDto> findAll() {
        return itemRepo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ItemDto get(Long id) {
        return itemRepo.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public ItemDto create(ItemDto dto) {
        Item entity = mapper.toEntity(dto);

        Country manufacturer = countryRepo.findById(dto.getManufacturerId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Manufacturer not found: " + dto.getManufacturerId()));

        entity.setManufacturer(manufacturer);

        Item saved = itemRepo.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public ItemDto update(Long id, ItemDto dto) {
        return itemRepo.findById(id)
                .map(ex -> {
                    ex.setName(dto.getName());
                    ex.setPrice(dto.getPrice());
                    ex.setQuantity(dto.getQuantity());

                    if (dto.getManufacturerId() != null) {
                        Country m = countryRepo.findById(dto.getManufacturerId())
                                .orElseThrow(() ->
                                        new IllegalArgumentException("Manufacturer not found: " + dto.getManufacturerId()));
                        ex.setManufacturer(m);
                    }

                    Item saved = itemRepo.save(ex);
                    return mapper.toDto(saved);
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        itemRepo.deleteById(id);
    }
}
