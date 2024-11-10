package com.PE.springprj.controllers;


import com.PE.springprj.entities.Administrateur;
import com.PE.springprj.services.AdministrateurServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/Administrateur")
public class AdministrateurController {

    @Autowired
    private AdministrateurServices administrateurServices;

    @GetMapping("")
    public String listAdministrateurs(Model model) {
        model.addAttribute("administrateur", administrateurServices.getAllAdministrateurs());
        return "personnel/administrateur/index";
    }
    @GetMapping("/create")
    public String createAdministrateur(Model model) {
        model.addAttribute("administrateur", new Administrateur());
        return "personnel/administrateur/create";
    }
    @PostMapping("/save")
    public String saveAdministrateur(@Valid @ModelAttribute("administrateur") Administrateur administrateur, BindingResult result,
                                     RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "personnel/administrateur/create";
        }

        administrateurServices.save(administrateur);
        redirectAttributes.addFlashAttribute("successMessage", "Administrateur saved successfully!");

        return "redirect:/Administrateur";
    }


    @GetMapping("/view/{id}")
    public String showUpdateAdministrateurForm(@PathVariable("id") Long id, Model model) {
        Administrateur administrateur = administrateurServices.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid administrateur Id:" + id));

        model.addAttribute("administrateur", administrateur);

        return "personnel/administrateur/update";
    }


    @PostMapping("/update/{id}")
    public String updateAdministrateur(@PathVariable("id") Long id, @Valid @ModelAttribute("administrateur") Administrateur administrateur, BindingResult result,
                                       RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            administrateur.setId(id);
            return "personnel/administrateur/update";
        }

        administrateurServices.update(administrateur);
        redirectAttributes.addFlashAttribute("successMessage", "Administrateur saved successfully!");

        return "redirect:/Administrateur";
    }

    @GetMapping("/delete/{id}")
    public String DeleteAdministrateur(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {

        administrateurServices.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Administrateur deleted successfully!");
        return "redirect:/Administrateur";
    }
}
