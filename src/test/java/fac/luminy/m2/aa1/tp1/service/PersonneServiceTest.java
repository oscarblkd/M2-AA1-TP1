package fac.luminy.m2.aa1.tp1.service;

import fac.luminy.m2.aa1.tp1.model.TypeVoiture;
import fac.luminy.m2.aa1.tp1.model.entity.DureeLocation;
import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import fac.luminy.m2.aa1.tp1.repository.PersonneRepository;
import fac.luminy.m2.aa1.tp1.repository.VoitureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonneServiceTest {

    @Mock
    private PersonneRepository personneRepository;

    @InjectMocks
    private PersonneService personneService;

    @Test
    public void chiffredaffaireTest(){
        Voiture voiture1 = new Voiture();
        voiture1.setId(1L);
        voiture1.setModele("Model S");
        voiture1.setMarque("Tesla");
        voiture1.setAnnee(2020);
        voiture1.setType(TypeVoiture.SUV);
        voiture1.setChevauxFiscaux(10);
        voiture1.setPrix(100);
        voiture1.setConsommation(15.0);
        voiture1.setCouleur("Red");
        voiture1.setDureeLocations(List.of(new DureeLocation(
                LocalDate.of(2025, 9, 8),
                LocalDate.of(2025, 9, 9))));

        Personne personne = new Personne();
        personne.setNom("Dupont");
        personne.setVoituresPossedees(List.of(voiture1));
        when(personneRepository.findByNom("Dupont")).
                thenReturn(personne);

        //Act
        float result = personneService.chiffreDaffaire("Dupont");
        assertEquals(100, result);
    }

    @Test
    public void chiffredaffaireInvalidProprietaireTest(){

    }

    @Test
    public void tauxLocationAnnuelTest(){

    }

    @Test
    public void tauxLocationAnnuelInvalidProprietaireTest(){

    }


}
