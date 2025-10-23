package fac.luminy.m2.aa1.tp1.repository;

import fac.luminy.m2.aa1.tp1.model.TypeVoiture;
import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Type;
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
    public void testFindByPreferences(){
        Voiture v1 = new Voiture();
        v1.setPrix(8.9);
        v1.setType(TypeVoiture.BERLINE);
        voitureRepository.save(v1);

        Voiture v2 = new Voiture();
        v2.setPrix(9.0);
        v2.setType(TypeVoiture.SUV);
        voitureRepository.save(v2);

        Voiture v3 = new Voiture();
        v3.setPrix(10.0);
        v3.setType(TypeVoiture.BERLINE);
        voitureRepository.save(v3);

        Voiture v4 = new Voiture();
        v4.setPrix(11.0);
        v4.setType(TypeVoiture.SUV);
        voitureRepository.save(v4);

        Voiture v5 = new Voiture();
        v5.setPrix(11.1);
        v5.setType(TypeVoiture.BERLINE);
        voitureRepository.save(v5);

        List<Voiture> result = voitureRepository.findByPreferences(10, List.of(TypeVoiture.SUV));

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(v2.getPrix(), result.get(0).getPrix());
        assertEquals(v4.getPrix(), result.get(1).getPrix());
    }

}
