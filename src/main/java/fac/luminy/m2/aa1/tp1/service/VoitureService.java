package fac.luminy.m2.aa1.tp1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fac.luminy.m2.aa1.tp1.model.TypeVoiture;
import fac.luminy.m2.aa1.tp1.model.dto.VoitureDTO;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import fac.luminy.m2.aa1.tp1.repository.VoitureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    /**

     */
    private List<VoitureDTO> convertVoitureListToVoitureDTO(List<Voiture> voitures) {
        List<VoitureDTO> dtos = new ArrayList<>();
        for (Voiture voiture : voitures) {
            dtos.add(new VoitureDTO(voiture));
        }
        return dtos;
    }

    /**
     * Récupère la liste des voitures pour un propriétaire donné.
     *
     * @param nomProprietaire le nom du propriétaire dont les voitures doivent être récupérées
     * @return une liste de {@link VoitureDTO} représentant les voitures du propriétaire
     */

    public List<VoitureDTO> recupererVoituresProprietaire(String nomProprietaire){
        log.info("Demande de recuperation des voitures pour le proprietaire avec le nom {}", nomProprietaire);
        List<VoitureDTO> listeRetour = new ArrayList<>();
        //Faire l'appel au repository pour recuperer la voiture a partir du nom du proprietaire
        List<Voiture> voituresProprio = voitureRepository.findByProprietaireNom(nomProprietaire);
        // Convertir les voitures en voituresDTO
        if(voituresProprio != null){
            voituresProprio.forEach(voiture -> {
                listeRetour.add(new VoitureDTO(voiture));
            });
        }
        // Retourner la liste des voitures
        log.info("{} voitures pour le proprietaire avec le nom {}",listeRetour.size(),nomProprietaire);
        return listeRetour;
    }

    /**
     * Récupère une liste de voiture pour un locataire en fonction de ses préférences.
     *
     * @param typesVoiture Liste de {@link TypeVoiture} correspondant aux préférences du locataire
     * @param prix Prix de {@link Double} correspondant au prix voulu du locataire
     * @return une liste de {@link VoitureDTO}
     */
    public List<VoitureDTO> filtrageVoitures(double prix,
                                             List<TypeVoiture> typesVoiture){
        if(typesVoiture.isEmpty()){
            typesVoiture = List.of(TypeVoiture.values());
        }
        List<Voiture> listeRepo = voitureRepository.findByPreferences(prix,typesVoiture);
        List<VoitureDTO> listeRetour = new ArrayList<>();
        if(listeRepo != null){
            listeRepo.forEach(voiture -> {
                listeRetour.add(new VoitureDTO(voiture));
            });
        }

        return listeRetour;
    }
}
