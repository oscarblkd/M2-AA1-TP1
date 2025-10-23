package fac.luminy.m2.aa1.tp1.service;

import fac.luminy.m2.aa1.tp1.model.TypeVoiture;
import fac.luminy.m2.aa1.tp1.model.entity.DureeLocation;
import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import fac.luminy.m2.aa1.tp1.repository.PersonneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        float result = personneService.chiffreDaffaire("Dupont");
        assertEquals(100, result);
    }

    @Test
    public void chiffredaffaireInvalidProprietaireTest(){

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


        when(personneRepository.findByNom("Dupont")).
                thenReturn(null);

        float result = personneService.chiffreDaffaire("Dupont");
        assertEquals(-1, result);

    }

    @Test
    public void tauxLocationAnnuelSameYearTest(){

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
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 12, 31))));

        Voiture voiture2 = new Voiture();
        voiture2.setId(1L);
        voiture2.setModele("Model S");
        voiture2.setMarque("Tesla");
        voiture2.setAnnee(2020);
        voiture2.setType(TypeVoiture.SUV);
        voiture2.setChevauxFiscaux(10);
        voiture2.setPrix(100);
        voiture2.setConsommation(15.0);
        voiture2.setCouleur("Red");
        voiture2.setDureeLocations(List.of());

        Personne personne = new Personne();
        personne.setNom("Dupont");
        personne.setVoituresPossedees(List.of(voiture1, voiture2));
        when(personneRepository.findByNom("Dupont")).
                thenReturn(personne);

        double taux =  personneService.tauxLocationAnnuel("Dupont", 2025);
        assertEquals(50.0, taux);

    }

    @Test
    public void tauxLocationAnnuelNotSameYearBeginTest(){
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
                LocalDate.of(2024, 12, 1),
                LocalDate.of(2025, 12, 31))));


        Personne personne = new Personne();
        personne.setNom("Dupont");
        personne.setVoituresPossedees(List.of(voiture1));
        when(personneRepository.findByNom("Dupont")).
                thenReturn(personne);

        double taux =  personneService.tauxLocationAnnuel("Dupont", 2025);
        assertEquals(100.0, taux);

    }

    @Test
    public void tauxLocationAnnuelNotSameEndYearTest(){

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
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2026, 1, 31))));


        Personne personne = new Personne();
        personne.setNom("Dupont");
        personne.setVoituresPossedees(List.of(voiture1));
        when(personneRepository.findByNom("Dupont")).
                thenReturn(personne);

        double taux =  personneService.tauxLocationAnnuel("Dupont", 2025);
        assertEquals(100.0, taux);

    }

    @Test
    public void tauxLocationAnnuelInvalidProprietaireTest(){

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
                LocalDate.of(2024, 12, 1),
                LocalDate.of(2025, 12, 31))));


        when(personneRepository.findByNom("Dupont")).
                thenReturn(null);

        double taux =  personneService.tauxLocationAnnuel("Dupont", 2025);
        assertEquals(-1, taux);

    }



}
