package com.PE.springprj.controllers;


import com.PE.springprj.entities.*;
import com.PE.springprj.services.*;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;



@Controller
@RequestMapping("/Examen")
public class ExamenController {

    @Autowired
    private ExamenServices examenServices;

    @Autowired
    private SalleServices salleServices;

    @Autowired
    private EnseignantServices enseignantServices;

    @Autowired
    private AdministrateurServices administrateurServices;

    @Autowired
    private ElementPedagogiqueServices elementPedagogiqueServices;


    @GetMapping("")
    public String listExamples(Model model) {
        model.addAttribute("examens", examenServices.getAllExamens());
        return "examen/index";
    }

    @GetMapping("/create")
    public String createExamen(Model model, HttpSession session) {

        session.removeAttribute("epreuveFilePath");
        session.removeAttribute("pvFilePath");

        ExamForm examForm = new ExamForm();
        List<Enseignant> enseignants = enseignantServices.getAllEnseignants();
        List<ElementPedagogique> elementsPedagogiques = elementPedagogiqueServices.getAllElementWithEnseignant();

        model.addAttribute("examForm", examForm);
        model.addAttribute("enseignants", enseignants);
        model.addAttribute("elementsPedagogiques", elementsPedagogiques);

        return "examen/create";
    }




    @PostMapping("/firstSave")
    public String firstSaveExamen(@ModelAttribute @Valid ExamForm examForm, Model model, HttpSession session,
                                  BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "examen/create";
        }


        String epreuveFilePath = saveFile(examForm.getEpreuveFile());
        String pvFilePath = saveFile(examForm.getPvFile());

        if (epreuveFilePath != null && pvFilePath != null) {
            session.setAttribute("epreuveFilePath", epreuveFilePath);
            session.setAttribute("pvFilePath", pvFilePath);

            try {
                long epreuveFileSize = Files.size(Paths.get(epreuveFilePath));
                long pvFileSize = Files.size(Paths.get(pvFilePath));
                long maxFileSize = 51200; // 50KB in bytes

                if (epreuveFileSize > maxFileSize || pvFileSize > maxFileSize) {
                    redirectAttributes.addFlashAttribute("successMessage", "File size exceeds the maximum limit of 50KB.");
                    return "redirect:/Examen/create";
                }
            } catch (IOException e) {

                System.out.println("********************** Exception occurred while getting file size: " + e.getMessage());
                redirectAttributes.addFlashAttribute("successMessage", "Error accessing file information.");
                return "redirect:/Examen/create";
            }
        }

        Long elementId = examForm.getElementId();
        String semester = examForm.getSemester();
        String year = examForm.getUniversityYear();
        String type = examForm.getTypeelement();
        String sessions = examForm.getContractType();

        if (examenServices.isExamenExists(elementId, semester, year, type, sessions)) {
            redirectAttributes.addFlashAttribute("successMessage", "This Exam already exists !");
            return "redirect:/Examen/create";
        }

        String date = examForm.getExamDate();
        String time = examForm.getStartTime();

        model.addAttribute("examDate", date);
        model.addAttribute("examTime", time);

        List<ElementPedagogique> elementsPedagogiques = elementPedagogiqueServices.getAllElements();
        model.addAttribute("elementsPedagogiques", elementsPedagogiques);

        model.addAttribute("examForm", examForm);
        return "examen/nextcreate";
    }

    @GetMapping("/table-row")
    public String getTableRow(Model model, @RequestParam String examDate, @RequestParam String examTime) {

        String examDateName = getDayNameFromDate(examDate);
        String examTimeRange = getTimeRange(examTime);

        List<Salle> salles = salleServices.getAllSalles();
        List<Administrateur> administrateurs = administrateurServices.getAllAdministrateurs();
        List<Enseignant> enseignants = enseignantServices.getEnseignantsByDayAndTime(examDateName, examTimeRange);

        model.addAttribute("salles", salles);
        model.addAttribute("administrateurs", administrateurs);
        model.addAttribute("enseignants", enseignants);

        return "examen/fragment";
    }

    @PostMapping("/save")
    public String SaveExamen(@ModelAttribute ExamForm examForm, @RequestParam Map<String, String> params,
                             HttpSession session, RedirectAttributes redirectAttributes) {

        List<Integer> salleValues = new ArrayList<>();
        List<Integer> adminValues = new ArrayList<>();
        List<Integer> supervisorValues = new ArrayList<>();

        List<Salle> salles = new ArrayList<>();
        List<Enseignant> enseignants = new ArrayList<>();
        List<Administrateur> administrateurs = new ArrayList<>();

        int count = 0;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String paramName = entry.getKey();
            if (paramName.startsWith("salle-")) {
                count++;
            }
        }

        for (int i = 1; i <= count; i++) {
            int j = 0;

            for (Map.Entry<String, String> entry : params.entrySet()) {
                String paramName = entry.getKey();
                if (paramName.startsWith("supervisors-" + i + "-")) {
                    j++;
                }
            }

            for (int k = 1; k <= j; k++) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String paramName = entry.getKey();
                    String paramValue = entry.getValue();

                    if (paramName.startsWith("salle-" + i)) {
                        salleValues.add(Integer.parseInt(paramValue));
                    } else if (paramName.startsWith("administrateurs-" + i)) {
                        adminValues.add(Integer.parseInt(paramValue));
                    }
                }
            }
        }

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = entry.getValue();
            if (paramName.startsWith("supervisors-")) {
                supervisorValues.add(Integer.parseInt(paramValue));
            }
        }

        Examen examen = new Examen();

        String epreuveFilePath = (String) session.getAttribute("epreuveFilePath");
        String pvFilePath = (String) session.getAttribute("pvFilePath");

        if (pvFilePath != null){
            try {
                byte[] pvBytes = Files.readAllBytes(Paths.get(pvFilePath));
                Path pvPath = Paths.get(pvFilePath);
                examen.setPvFileName(pvPath.getFileName().toString());
                examen.setPv(pvBytes);

            } catch (IOException e) {
                System.out.println("********************** Exception occurred while reading file: " + e.getMessage());
            }
        }
        if (epreuveFilePath != null){
            try {
                byte[] epreuveBytes = Files.readAllBytes(Paths.get(epreuveFilePath));
                Path epreuvePath = Paths.get(epreuveFilePath);
                examen.setEpreuveFileName(epreuvePath.getFileName().toString());
                examen.setEpreuve(epreuveBytes);
            } catch (IOException e) {
                System.out.println("********************** Exception occurred while reading file: " + e.getMessage());
            }
        }

        examen.setSemestre(examForm.getSemester());
        examen.setSession(examForm.getContractType());
        examen.setType(examForm.getTypeelement());
        examen.setDate(examForm.getExamDate());
        examen.setHeureDepart(examForm.getStartTime());
        examen.setDureePrevue(examForm.getExpectedDuration());
        examen.setDureeReelle(examForm.getActualDuration());
        examen.setAnneeUniversitaire(examForm.getUniversityYear());
        examen.setRapport(examForm.getRapport());


        Enseignant supervisor = enseignantServices.findById(examForm.getCoordinatorId()).get();
        ElementPedagogique elementPedagogique = elementPedagogiqueServices.findById(examForm.getElementId()).get();
        examen.setEnseignant(supervisor);
        examen.setElementPedagogique(elementPedagogique);


        if (salleValues.size() == adminValues.size() && adminValues.size() == supervisorValues.size()) {
            int tailleListe = salleValues.size();
            for (int i = 0; i < tailleListe; i++) {
                int salleValue = salleValues.get(i);
                int adminValue = adminValues.get(i);
                int supervisorValue = supervisorValues.get(i);
                Long salleId = (long) salleValue;
                Long adminId = (long) adminValue;
                Long supervisorId = (long) supervisorValue;

                Salle salle = salleServices.findById(salleId).get();
                Enseignant enseignant = enseignantServices.findById(supervisorId).get();
                Administrateur administrateur = administrateurServices.findById(adminId).get();

                salles.add(salle);
                enseignants.add(enseignant);
                administrateurs.add(administrateur);
            }
        } else {
            System.out.println("Les listes n'ont pas la même taille.");
        }


        examenServices.saveExamenWithAssociations(examen, salles, enseignants, administrateurs);

        redirectAttributes.addFlashAttribute("successMessage", "Exam save successfully!");

        return "redirect:/Examen";
    }

    @GetMapping("/delete/{id}")
    public String DeleteExample(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {

        examenServices.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Exam deleted successfully!");
        return "redirect:/Examen";
    }




    public static String getDayNameFromDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        String dayName = date.getDayOfWeek().toString();
        dayName = dayName.substring(0, 1).toUpperCase() + dayName.substring(1).toLowerCase();

        return dayName;
    }

    public static String getTimeRange(String hour) {
        hour = hour.trim();

        switch (hour) {
            case "08:00":
                return "08:00 - 10:00";
            case "10:00":
                return "10:00 - 12:00";
            case "14:00":
                return "14:00 - 16:00";
            case "16:00":
                return "16:00 - 18:00";
            default:
                return "Invalid hour";
        }
    }

    private String saveFile(MultipartFile file) {
        try {
            // Save the file to a temporary location
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(System.getProperty("java.io.tmpdir"), fileName);
            Files.write(filePath, file.getBytes());
            return filePath.toString();
        } catch (IOException e) {
            // Handle the exception
            System.out.println("********************** Exception occurred while saving file: " + e.getMessage());
            return null;
        }
    }

}
