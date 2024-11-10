package com.PE.springprj.controllers;


import com.PE.springprj.entities.Enseignant;
import org.slf4j.Logger;
import com.PE.springprj.entities.Group;
import com.PE.springprj.services.EnseignantServices;
import com.PE.springprj.services.GroupServices;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/Group")
public class GroupController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupServices groupServices;

    @Autowired
    private EnseignantServices enseignantServices;

    @GetMapping("")
    public String listGroups(Model model) {
        model.addAttribute("groups", groupServices.getAllGroups());

        return "personnel/group/index";
    }

    @GetMapping("/create")
    public String createGroup(Model model) {
        model.addAttribute("enseignant", enseignantServices.getAllEnseignants());
        model.addAttribute("group", new Group());

        return "personnel/group/create";
    }

    @GetMapping("/create/ByFiliere/{filier}")
    public String createGroupByFiliere(@PathVariable("filier") String filier,Model model) {
        model.addAttribute("enseignant", enseignantServices.findByFiliere(filier));
        model.addAttribute("group", new Group());

        return "personnel/group/create";
    }

    @GetMapping("/create/ByDepartement/{departement}")
    public String createGroupByDepartement(@PathVariable("departement") String departement,Model model) {
        model.addAttribute("enseignant", enseignantServices.findByDepartement(departement));
        model.addAttribute("group", new Group());

        return "personnel/group/create";
    }

    @PostMapping("/save")
    public String saveGroup(@Valid Group group, BindingResult result,
                            @RequestParam(value = "enseignants", required = false) List<Long> enseignantIds,
                            RedirectAttributes redirectAttributes) {

        logger.info("Starting saveGroup method");

        if (result.hasErrors()) {
            logger.warn("Validation errors: {}", result.getAllErrors());
            return "personnel/group/create";
        }

        /*group.setEnseignants(enseignantServices.findByIds(enseignantId));*/

        if (enseignantIds != null && !enseignantIds.isEmpty()) {
            logger.info("Enseignant IDs provided: {}", enseignantIds);
            Set<Enseignant> selectedEnseignants = new HashSet<>();
            for (Long enseignantId : enseignantIds) {
                Optional<Enseignant> optionalEnseignant = enseignantServices.findById(enseignantId);
                if (optionalEnseignant.isPresent()) {
                    selectedEnseignants.add(optionalEnseignant.get());
                    logger.info("Added enseignant with ID: {}", enseignantId);
                } else {
                    logger.warn("Enseignant with ID {} not found", enseignantId);
                }
            }
            group.setEnseignants(selectedEnseignants);
        }

        logger.info("Saving group: {}", group);
        try {
            groupServices.save(group);
        } catch (Exception ex) {
            logger.error("Error occurred while saving group: ", ex);
            throw ex;
        }

        logger.info("Group saved successfully");
        redirectAttributes.addFlashAttribute("successMessage", "Group saved successfully!");

        return "redirect:/Group";
    }

    @GetMapping("/view/{id}")
    public String showUpdateGroupForm(@PathVariable("id") Long id, Model model) {
        Group group = groupServices.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id:" + id));

        model.addAttribute("enseignant", enseignantServices.getAllEnseignants());
        model.addAttribute("group", group);

        return "personnel/group/update";
    }


    @GetMapping("/delete/{id}")
    public String DeleteGroup(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {

        groupServices.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Group deleted successfully!");
        return "redirect:/Group";
    }



    @PostMapping("/update/{id}")
    public String updateGroup(@PathVariable("id") Long id, @Valid Group group, BindingResult result,
                              @RequestParam(value = "enseignants", required = false) List<Long> enseignantIds,
                              RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            group.setId(id);
            return "personnel/group/update";
        }

        if (enseignantIds != null && !enseignantIds.isEmpty()) {
            Set<Enseignant> selectedEnseignants = new HashSet<>();
            for (Long enseignantId : enseignantIds) {
                Optional<Enseignant> optionalEnseignant = enseignantServices.findById(enseignantId);
                if (optionalEnseignant.isPresent()) {
                    selectedEnseignants.add(optionalEnseignant.get());
                }
            }
            group.setEnseignants(selectedEnseignants);
        }

        groupServices.update(group);
        redirectAttributes.addFlashAttribute("successMessage", "Group saved successfully!");

        return "redirect:/Group";
    }


}
