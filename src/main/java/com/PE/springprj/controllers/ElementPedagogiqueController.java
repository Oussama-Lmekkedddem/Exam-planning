package com.PE.springprj.controllers;

import com.PE.springprj.entities.ElementPedagogique;
import com.PE.springprj.entities.Enseignant;
import com.PE.springprj.services.EnseignantServices;
import com.PE.springprj.services.ElementPedagogiqueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;


@Controller
@RequestMapping("/ElementPedagogique")
public class ElementPedagogiqueController {

    @Autowired
    private ElementPedagogiqueServices elementPedagogiqueService;

    @Autowired
    private EnseignantServices enseignantServices;

    @GetMapping("")
    public String listElements(Model model) {
        model.addAttribute("elements", elementPedagogiqueService.getAllElements());

        return "elementPedagogique/index";
    }

    @GetMapping("/create")
    public String createElement(Model model) {
        model.addAttribute("enseignants", enseignantServices.getAllEnseignants());
        model.addAttribute("elementPedagogique", new ElementPedagogique());
        return "elementPedagogique/create";
    }

    @PostMapping("/save")
    public String saveElement(@Valid @ModelAttribute ElementPedagogique elementPedagogique,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "elementPedagogique/create";
        }

        try {
            Enseignant enseignant = enseignantServices.findById(elementPedagogique.getEnseignant().getId())
                    .orElseThrow(() -> new RuntimeException("Enseignant not found"));


            elementPedagogique.setFiliere(enseignant.getFiliere());
            elementPedagogique.setEnseignant(enseignant);

            elementPedagogiqueService.save(elementPedagogique);
            redirectAttributes.addFlashAttribute("successMessage", "Element Pedagogique saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while saving Element Pedagogique.");
            throw new RuntimeException(e);
        }

        return "redirect:/ElementPedagogique";
    }





    @GetMapping("/view/{id}")
    public String showUpdateElementForm(@PathVariable("id") Long id, Model model) {
        ElementPedagogique elementPedagogique = elementPedagogiqueService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Element Pedagogique Id:" + id));

        model.addAttribute("enseignants", enseignantServices.getAllEnseignants());
        model.addAttribute("elementPedagogique", elementPedagogique);

        return "elementPedagogique/update";
    }

    @PostMapping("/update/{id}")
    public String updateElement(@PathVariable("id") Long id, @Valid @ModelAttribute("elementPedagogique") ElementPedagogique elementPedagogique,
                                BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            elementPedagogique.setId(id);
            return "elementPedagogique/update";
        }


        try {
            Enseignant enseignant = enseignantServices.findById(elementPedagogique.getEnseignant().getId())
                    .orElseThrow(() -> new RuntimeException("Enseignant not found"));

            elementPedagogique.setFiliere(enseignant.getFiliere());
            elementPedagogique.setEnseignant(enseignant);

            elementPedagogiqueService.update(elementPedagogique);
            redirectAttributes.addFlashAttribute("successMessage", "Element Pedagogique updated successfully!");
            return "redirect:/ElementPedagogique";
        }
        catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Error updating ElementPedagogique: " + e.getMessage(), e);
    }
    }


    @GetMapping("/create/ByFiliere/{filiere}")
    public String createByFiliere(@PathVariable("filiere") String filiere, Model model) {
        List<Enseignant> enseignants = enseignantServices.findByFiliere(filiere);

        ElementPedagogique elementPedagogique = new ElementPedagogique();
        elementPedagogique.setFiliere(filiere);

        model.addAttribute("enseignants", enseignants);
        model.addAttribute("elementPedagogique", elementPedagogique);

        return "elementPedagogique/create";
    }


    @GetMapping("/delete/{id}")
    public String DeleteGroup(@PathVariable("id") Long id,
                              RedirectAttributes redirectAttributes) {

        elementPedagogiqueService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Element Pedagogique deleted successfully!");
        return "redirect:/ElementPedagogique";
    }

}
