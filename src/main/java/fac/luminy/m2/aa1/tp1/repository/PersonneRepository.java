package fac.luminy.m2.aa1.tp1.repository;

import fac.luminy.m2.aa1.tp1.model.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
     Personne findByNom(@Param("nom") String nom);
}
