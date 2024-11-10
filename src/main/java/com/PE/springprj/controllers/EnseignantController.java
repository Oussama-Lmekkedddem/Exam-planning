package com.PE.springprj.controllers;

import com.PE.springprj.entities.Enseignant;
import com.PE.springprj.entities.TempsLibre;
import com.PE.springprj.handlers.GlobalExceptionHandler;
import com.PE.springprj.repositories.EnseignantRepository;
import com.PE.springprj.repositories.TempsLibreRepository;
import com.PE.springprj.services.EnseignantServices;
import com.PE.springprj.services.TempsLibreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/Enseignant")
public class EnseignantController {

    @Autowired
    private TempsLibreService tempsLibreService;

    @Autowired
    private EnseignantServices enseignantServices;

    @Autowired
    private TempsLibreRepository tempsLibreRepository;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @GetMapping("")
    public String listEnseignants(Model model) {
       model.addAttribute("enseignant", enseignantServices.getAllEnseignants());
        return "personnel/enseignant/index";
    }

    @GetMapping("/create")
    public String createEnseignant(Model model) {
        model.addAttribute("enseignant", new Enseignant());
        return "personnel/enseignant/create";
    }

    @PostMapping("/save")
    public String saveEnseignant(@Valid @ModelAttribute("enseignant") Enseignant enseignant, BindingResult result,
                                     RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "personnel/enseignant/create";
        }

        enseignantServices.save(enseignant);
        redirectAttributes.addFlashAttribute("successMessage", "Enseignant saved successfully!");

        return "redirect:/Enseignant";
    }


    @GetMapping("/view/{id}")
    public String showUpdateEnseignantForm(@PathVariable("id") Long id, Model model) {
        Enseignant enseignant = enseignantServices.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid enseignant Id:" + id));

        model.addAttribute("enseignant", enseignant);

        return "personnel/enseignant/update";
    }


    @PostMapping("/update/{id}")
    public String updateEnseignant(@PathVariable("id") Long id, @Valid @ModelAttribute("enseignant") Enseignant enseignant, BindingResult result,
                                       RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            enseignant.setId(id);
            return "personnel/enseignant/update";
        }

        enseignantServices.update(enseignant);
        redirectAttributes.addFlashAttribute("successMessage", "Enseignant saved successfully!");

        return "redirect:/Enseignant";
    }

    @GetMapping("/delete/{id}")
    public String DeleteEnseignant(@PathVariable("id") Long id,
                                       RedirectAttributes redirectAttributes) {

        enseignantServices.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Enseignant deleted successfully!");
        return "redirect:/Enseignant";
    }

    @GetMapping("/view/tempslibre/{id}")
    public String showUpdateTempLibre(@PathVariable("id") Long id, Model model) {
        Logger logger = LoggerFactory.getLogger(getClass());

        try {
            Enseignant enseignant = enseignantServices.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid enseignant Id:" + id));

            List<TempsLibre> tempsLibre = tempsLibreRepository.findByEnseignantIdOrderByIdAsc(id);

            model.addAttribute("enseignant", enseignant);
            model.addAttribute("tempsLibre", tempsLibre);

            return "personnel/enseignant/tempslibre";
        } catch (IllegalArgumentException e) {
            logger.error("Error fetching enseignant with Id: {}", id, e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while processing temps libre for enseignant Id: {}", id, e);
            throw new RuntimeException("Unexpected error occurred");
        }
    }

    @PostMapping("/update/tempslibre")
    public String updateTempsLibre(@RequestParam("tempslibres_Id") List<Long> ids,
                                   @RequestParam("tempslibres_intervalExiste") List<Boolean> intervalesExistent,
                                   RedirectAttributes redirectAttributes) {

        for (int i = 0; i < ids.size(); i++) {
            Long tempsLibreId = ids.get(i);
            Boolean intervalExiste = intervalesExistent.get(i);
            tempsLibreService.updateIntervalExiste(tempsLibreId, intervalExiste);
        }
        redirectAttributes.addFlashAttribute("successMessage", "Time saved successfully!");

        return "redirect:/Enseignant";
    }

}
