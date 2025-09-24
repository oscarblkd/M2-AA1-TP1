package fac.luminy.m2.aa1.tp1.repository;

import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VoitureRepositoryTest {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Test
    public void testFindByProprietaireNom() {
        // Arrange
        Voiture voiture = new Voiture();

        Personne proprietaire = new Personne();
        proprietaire.setNom("Greenwood");
        personneRepository.save(proprietaire);

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

    @Test
    public void testFindByPricePlusMinusTenPercent(){
        Voiture v1 = new Voiture();
        v1.setPrix(8.9);
        voitureRepository.save(v1);

        Voiture v2 = new Voiture();
        v2.setPrix(9.0);
        voitureRepository.save(v2);

        Voiture v3 = new Voiture();
        v3.setPrix(10.0);
        voitureRepository.save(v3);

        Voiture v4 = new Voiture();
        v4.setPrix(11.0);
        voitureRepository.save(v4);

        Voiture v5 = new Voiture();
        v5.setPrix(11.1);
        voitureRepository.save(v5);

        List<Voiture> result = voitureRepository.findByPricePlusMinusTenPercent(10);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(v2.getPrix(), result.get(0).getPrix());
        assertEquals(v3.getPrix(), result.get(1).getPrix());
        assertEquals(v4.getPrix(), result.get(2).getPrix());

    }
}
