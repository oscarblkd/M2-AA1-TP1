package fac.luminy.m2.aa1.tp1.repository;

import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VoitureRepositoryTest {

    @Autowired
    private VoitureRepository voitureRepository;

    @Test
    public void testFindByProprietaireNom() {
        // Arrange
        Voiture voiture = new Voiture();

        Personne proprietaire = new Personne();
        proprietaire.setNom("Greenwood");
        voiture.setProprietaire(proprietaire);
        voiture.setMarque("Ferrari");
        voitureRepository.save(voiture);

        // Act
        List<Voiture> result = voitureRepository.findByProprietaireNom("Greenwood");

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Greenwood", result.get(0).getProprietaire().getNom());
    }

    @Test
    public void testFindByProprietaireNom_NotFound() {
        // Act
        List<Voiture> result = voitureRepository.findByProprietaireNom("NonExistent");

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
