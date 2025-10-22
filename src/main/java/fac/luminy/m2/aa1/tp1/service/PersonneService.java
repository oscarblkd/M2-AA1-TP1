package fac.luminy.m2.aa1.tp1.service;

import fac.luminy.m2.aa1.tp1.model.dto.VoitureDTO;
import fac.luminy.m2.aa1.tp1.model.entity.DureeLocation;
import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import fac.luminy.m2.aa1.tp1.repository.PersonneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class PersonneService {

     @Autowired
     private PersonneRepository personneRepository;

     /**
      * Calcul le chiffre d'affaires d'une personne donnée.
      * @param nomProprietaire le nom du propriétaire donnée
      * @return le chiffre d'affaires de la personne
      */
     public float chiffreDaffaire(String nomProprietaire){
          Personne proprietaire = personneRepository.findByNom(nomProprietaire);
          if(proprietaire == null){
               return -1;
          }
          List<Voiture> voitures = proprietaire.getVoituresPossedees();
          float chiffreDaffaire = 0;
          for (Voiture voiture : voitures) {
               chiffreDaffaire += calculRevenueVoiture(voiture);
          }
          return chiffreDaffaire;
     }

     public float tauxLocationAnnuel(String nomProprietaire, int annee){
          Personne proprietaire = personneRepository.findByNom(nomProprietaire);
          if(proprietaire == null){
               return -1;
          }
          List<Voiture> voitures = proprietaire.getVoituresPossedees();
          float jours = 1f;
          for (Voiture voiture : voitures) {
               for(DureeLocation dureeLocation : voiture.getDureeLocations()) {
                   if(dureeLocation.dateDebut().getYear() == annee && dureeLocation.dateFin().getYear() == annee){
                       jours += ChronoUnit.DAYS.between(dureeLocation.dateDebut(), dureeLocation.dateFin());
                   }
                   else if (dureeLocation.dateDebut().getYear() == annee && dureeLocation.dateFin().getYear() != annee) {
                       jours += ChronoUnit.DAYS.between(dureeLocation.dateDebut(), LocalDate.of(annee, 12, 31));
                   }
                   else if (dureeLocation.dateDebut().getYear() != annee && dureeLocation.dateFin().getYear() == annee) {
                       jours += ChronoUnit.DAYS.between(LocalDate.of(annee, 1, 1), dureeLocation.dateFin());
                   }
               }
          }

          return  Year.isLeap(annee) ?
                   ((jours / proprietaire.getVoituresPossedees().size()) * 100f / 366):
                   ((jours / proprietaire.getVoituresPossedees().size()) * 100f / 365);
     }

     private float calculRevenueVoiture(Voiture voiture) {
          float result = 0;
          for(DureeLocation dureeLocation : voiture.getDureeLocations()) {
               result += (float) (voiture.getPrix() * ChronoUnit.DAYS.between(dureeLocation.dateDebut(), dureeLocation.dateFin()));
          }
          return result;
     }
}
