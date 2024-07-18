package fr.eni.projet.cliniqueveterinaire.controller;

import fr.eni.projet.cliniqueveterinaire.bll.PersonnelService;
import fr.eni.projet.cliniqueveterinaire.bo.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/personnels")
public class ControllerPersonnel {

    private final PersonnelService personnels;

    @Autowired
    public ControllerPersonnel(PersonnelService personnels) {
        this.personnels = personnels;
    }

    @GetMapping
    public String afficherPersonnels(Model model) {
        List<Personnel> personnel = personnels.afficherToutPersonnel();
        model.addAttribute("personnel", personnel);
        return "view-personnels";
    }
}
