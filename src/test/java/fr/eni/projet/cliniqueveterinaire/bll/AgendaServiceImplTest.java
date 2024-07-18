package fr.eni.projet.cliniqueveterinaire.bll;

import fr.eni.projet.cliniqueveterinaire.bo.RendezVous;
import fr.eni.projet.cliniqueveterinaire.dal.RendezVousDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgendaServiceImplTest {

    @Mock
    private RendezVousDAO rendezVousDAO;

    @InjectMocks
    private AgendaServiceImpl agendaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreerRendezVous() {
        RendezVous rendezVous = new RendezVous();
        agendaService.creerRendezVous(rendezVous);
        verify(rendezVousDAO, times(1)).create(rendezVous);
    }

    @Test
    void testModifierRendezVous() {
        RendezVous rendezVous = new RendezVous();
        agendaService.modifierRendezVous(rendezVous);
        verify(rendezVousDAO, times(1)).update(rendezVous);
    }

    @Test
    void testSupprimerRendezVous() {
        long id = 1L;
        agendaService.supprimerRendezVous(id);
        verify(rendezVousDAO, times(1)).delete(id);
    }

    @Test
    void testRechercherRendezVousParId() {
        long id = 1L;
        when(rendezVousDAO.read(id)).thenReturn(new RendezVous());
        RendezVous rendezVous = agendaService.rechercherRendezVousParId(id);
        verify(rendezVousDAO, times(1)).read(id);
        assertNotNull(rendezVous);
    }

    @Test
    void testRechercherRendezVousParVeterinaireEtDate() {
        long codeVeto = 1L;
        LocalDate date = LocalDate.now();
        when(rendezVousDAO.findByVeterinaireAndDate(codeVeto, date)).thenReturn(List.of(new RendezVous()));
        List<RendezVous> rendezVousList = agendaService.rechercherRdvByVetEtDate(codeVeto, date);
        verify(rendezVousDAO, times(1)).findByVeterinaireAndDate(codeVeto, date);
        assertFalse(rendezVousList.isEmpty());
    }

    @Test
    void testRechercherRendezVousParDate() {
        LocalDate date = LocalDate.now();
        when(rendezVousDAO.findByDate(date)).thenReturn(List.of(new RendezVous()));
        List<RendezVous> rendezVousList = agendaService.rechercherRendezVousParDate(date);
        verify(rendezVousDAO, times(1)).findByDate(date);
        assertFalse(rendezVousList.isEmpty());
    }
}

