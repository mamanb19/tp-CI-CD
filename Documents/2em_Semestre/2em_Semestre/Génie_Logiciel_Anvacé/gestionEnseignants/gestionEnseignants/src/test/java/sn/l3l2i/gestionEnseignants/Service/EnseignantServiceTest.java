package sn.l3l2i.gestionEnseignants.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sn.l3l2i.gestionEnseignants.models.Enseignant;
import sn.l3l2i.gestionEnseignants.repository.EnseignantRepository;
import sn.l3l2i.gestionEnseignants.service.EnseignantService;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnseignantServiceTest {

    @Mock
    private EnseignantRepository enseignantRepository;
    @InjectMocks
    private EnseignantService enseignantService;

    @Test
    public void testAjouterEnseignant() {
        Enseignant enseignant = new Enseignant(1L, "Doe", "John","johndoe@exemple.com");

        when(enseignantRepository.save(any(Enseignant.class))).thenReturn(enseignant);

        Enseignant savedEnseignant = enseignantService.ajouterEnseignant(enseignant);

        assertNotNull(savedEnseignant);
        assertEquals("Doe", savedEnseignant.getNom());
        verify(enseignantRepository, times(1)).save(enseignant);
    }

    @Test
    public void testTrouverEnseignantParId_NotFound() {
        when(enseignantRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            enseignantService.trouverEnseignantParId(1L);
        });
    }

    @Test
    public void testAjouterEnseignant_Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            enseignantService.ajouterEnseignant(null);
        });
    }
}
