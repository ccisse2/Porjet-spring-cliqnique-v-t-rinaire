package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.Personnel;
import fr.eni.projet.cliniqueveterinaire.dal.PersonnelDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PersonnelServiceImplTest {

    @Mock
    private PersonnelDAO personnelDAO;

    @InjectMocks
    private PersonnelServiceImpl personnelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterPersonnel() {
        Personnel personnel = new Personnel();
        personnelService.ajouterPersonnel(personnel);
        verify(personnelDAO, times(1)).create(personnel);
    }

    @Test
    void testModifierPersonnel() {
        Personnel personnel = new Personnel();
        personnelService.modifierPersonnel(personnel);
        verify(personnelDAO, times(1)).update(personnel);
    }

    @Test
    void testSupprimerPersonnel() {
        long codePers = 1L;
        personnelService.supprimerPersonnel(codePers);
        verify(personnelDAO, times(1)).delete(codePers);
    }

    @Test
    void testRechercherPersonnelParId() {
        long codePers = 1L;
        when(personnelDAO.read(codePers)).thenReturn(new Personnel());
        Personnel personnel = personnelService.rechercherPersonnelParId(codePers);
        verify(personnelDAO, times(1)).read(codePers);
        assertNotNull(personnel);
    }

    @Test
    void testReinitialiserMotPasse() {
        long codePers = 1L;
        String nouveauMotPasse = "newPassword";
        Personnel personnel = new Personnel();
        personnel.setCodePers(codePers);
        when(personnelDAO.read(codePers)).thenReturn(personnel);
        personnelService.reinitialiserMotPasse(codePers, nouveauMotPasse);
        verify(personnelDAO, times(1)).update(personnel);
        assertEquals(nouveauMotPasse, personnel.getMotPasse());
    }

    @Test
    void testRechercherToutPersonnel() {
        when(personnelDAO.findAll()).thenReturn(List.of(new Personnel()));
        List<Personnel> personnels = personnelService.rechercherToutPersonnel();
        verify(personnelDAO, times(1)).findAll();
        assertFalse(personnels.isEmpty());
    }
}
