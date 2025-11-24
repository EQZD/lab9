package lab9.controller;

import lab9.dto.CountryDto;
import lab9.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/countries")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) { this.service = service; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("countries", service.findAll());
        return "countries/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("country", new CountryDto());
        return "countries/form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("country") @Valid CountryDto dto) {
        service.create(dto);
        return "redirect:/countries";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("country", service.get(id));
        return "countries/form";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute("country") @Valid CountryDto dto) {
        service.update(id, dto);
        return "redirect:/countries";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/countries";
    }
}