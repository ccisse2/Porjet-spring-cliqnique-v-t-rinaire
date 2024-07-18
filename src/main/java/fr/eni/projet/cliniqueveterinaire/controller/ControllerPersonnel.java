package fr.eni.projet.cliniqueveterinaire.controller;

import fr.eni.projet.cliniqueveterinaire.bll.PersonnelService;
import fr.eni.projet.cliniqueveterinaire.bo.Personnel;
import fr.eni.projet.cliniqueveterinaire.bo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/personnels")
@SessionAttributes({"roleEnSession"})
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

    @ModelAttribute("roleEnSession")
    public List<Role> chargerRoles() {
        return personnels.consulterTousRoles();
    }

    @GetMapping("/creer")
    public String afficherFormulaireCreationPersonnel(Model model) {
        model.addAttribute("personnel", new Personnel());
        return "view-personnels-form";
    }

    @PostMapping("/creer")
    public String creerPersonnel(@ModelAttribute Personnel personnel) {
        personnels.ajouterPersonnel(personnel);
        return "redirect:/personnels";
    }
}
