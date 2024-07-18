package fr.eni.projet.cliniqueveterinaire.controller;

import fr.eni.projet.cliniqueveterinaire.bll.PersonnelService;
import fr.eni.projet.cliniqueveterinaire.bo.Personnel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ControllerPersonnelTest {

    @Mock
    private PersonnelService personnelService;

    @Mock
    private Model model;

    @InjectMocks
    private ControllerPersonnel controllerPersonnel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAfficherPersonnels() {
        List<Personnel> personnelList = List.of(new Personnel());
        when(personnelService.afficherToutPersonnel()).thenReturn(personnelList);

        String viewName = controllerPersonnel.afficherPersonnels(model);
        verify(personnelService, times(1)).afficherToutPersonnel();
        verify(model, times(1)).addAttribute("personnel", personnelList);
        assertEquals("view-personnel", viewName);
    }
}
