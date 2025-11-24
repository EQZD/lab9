package lab9.сontroller;

import lab9.dto.ItemDto;
import lab9.service.CountryService;
import lab9.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final CountryService countryService;

    public ItemController(ItemService itemService, CountryService countryService) {
        this.itemService = itemService;
        this.countryService = countryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "items/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new ItemDto());
        model.addAttribute("countries", countryService.findAll());
        return "items/form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("item") @Valid ItemDto dto) {
        itemService.create(dto);
        return "redirect:/items";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemService.get(id));
        model.addAttribute("countries", countryService.findAll());
        return "items/form";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute("item") @Valid ItemDto dto) {
        itemService.update(id, dto);
        return "redirect:/items";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        itemService.delete(id);
        return "redirect:/items";
    }
}