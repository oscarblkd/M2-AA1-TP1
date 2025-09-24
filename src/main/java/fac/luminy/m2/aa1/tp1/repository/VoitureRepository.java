package fac.luminy.m2.aa1.tp1.repository;

import fac.luminy.m2.aa1.tp1.model.TypeVoiture;
import fac.luminy.m2.aa1.tp1.model.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    List<Voiture> findByProprietaireNom(@Param("nom") String nom);

    @Query("SELECT v from VOITURE v where ?1 < v.prix * 1.10 or ?1 > v.prix * 0.90")
    List<Voiture> findByPricePlusMinusTenPercent(@Param("prix") int prix);

    List<Voiture> findVoituresByTypeIn(List<TypeVoiture> typesVoiture);
}
