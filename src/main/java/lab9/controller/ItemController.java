package lab9.controller;

import lab9.dto.ItemDto;
import lab9.service.CountryService;
import lab9.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService items;
    private final CountryService countries;

    public ItemController(ItemService items, CountryService countries) {
        this.items = items;
        this.countries = countries;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", items.findAll());
        return "items/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new ItemDto());
        model.addAttribute("countries", countries.findAll());
        return "items/form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("item") ItemDto dto) {
        items.create(dto);
        return "redirect:/items";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        var dto = items.get(id);
        model.addAttribute("item", dto);
        model.addAttribute("countries", countries.findAll());
        return "items/form";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       @ModelAttribute("item") ItemDto dto) {
        dto.setId(id);
        var updated = items.update(id, dto);
        return "redirect:/items";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        items.delete(id);
        return "redirect:/items";
    }
}
