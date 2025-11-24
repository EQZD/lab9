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
public class ItemService {
    private final ItemRepository itemRepo;
    private final CountryRepository countryRepo;
    private final ItemMapper mapper;

    public ItemService(ItemRepository itemRepo, CountryRepository countryRepo, ItemMapper mapper) {
        this.itemRepo = itemRepo;
        this.countryRepo = countryRepo;
        this.mapper = mapper;
    }

    public List<ItemDto> findAll() {
        return itemRepo.findAll().stream().map(mapper::toDto).toList();
    }

    public ItemDto get(Long id) {
        return itemRepo.findById(id).map(mapper::toDto).orElse(null);
    }

    @Transactional
    public ItemDto create(ItemDto dto) {
        Item entity = mapper.toEntity(dto);
        Country manufacturer = countryRepo.findById(dto.getManufacturerId())
                .orElseThrow(() -> new IllegalArgumentException("Manufacturer not found: " + dto.getManufacturerId()));
        entity.setManufacturer(manufacturer);
        return mapper.toDto(itemRepo.save(entity));
    }

    @Transactional
    public ItemDto update(Long id, ItemDto dto) {
        return itemRepo.findById(id).map(ex -> {
            ex.setName(dto.getName());
            ex.setPrice(dto.getPrice());
            ex.setQuantity(dto.getQuantity());
            if (dto.getManufacturerId() != null) {
                Country m = countryRepo.findById(dto.getManufacturerId())
                        .orElseThrow(() -> new IllegalArgumentException("Manufacturer not found: " + dto.getManufacturerId()));
                ex.setManufacturer(m);
            }
            return mapper.toDto(itemRepo.save(ex));
        }).orElse(null);
    }

    public void delete(Long id) {
        itemRepo.deleteById(id);
    }
}