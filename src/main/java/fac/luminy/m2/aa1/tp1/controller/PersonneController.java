package fac.luminy.m2.aa1.tp1.controller;

import fac.luminy.m2.aa1.tp1.model.dto.VoitureDTO;
import fac.luminy.m2.aa1.tp1.service.PersonneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour gérer les opérations liées aux personnes.
 * Fournit des points d'accès pour récupérer les informations des personnes
 * en fonction de son nom.
 *
 * @author moktar et oscar :)
 */
@RestController
@RequestMapping("personnes/")
@Slf4j
@AllArgsConstructor
public class PersonneController {

     PersonneService service;

     /**
      * Récupère le chiffre d'affaires d'une personne.
      *
      * @param nom le nom du propriétaire dont le chiffre d'affaires doit être récupérée
      * @return le chiffre d'affaires de la personne
      */
     @Operation(summary = "Récupère le chiffre d'affaire pour un propriétaire",
             description = "Retourne le chiffre d'affaire pour le propriétaire spécifié par son nom")
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Chiffre d'affaires récupérée avec succès"),
             @ApiResponse(responseCode = "404", description = "Propriétaire non trouvé"),
             @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
     })
     @GetMapping("revenue/{nom}")
     public ResponseEntity<Float> getChiffreDaffaire(
             @Parameter(description = "Le nom du propriétaire dont ont veux récupérer les revenus", required = true)
             @PathVariable String nom) {
          float result = service.chiffreDaffaire(nom);
          if(result == -1){
               return ResponseEntity.notFound().build();
          }
          return ResponseEntity.ok(result);
     }

     /**
      * Récupère le taux annuel d'une personne.
      *
      * @param nom le nom du propriétaire dont le taux annuel doit être récupérée
      * @return le taux annuel de la personne
      */
     @Operation(summary = "Récupère le taux annuel de location des voitures d'une personne",
             description = "Retourne le taux annuel de location pour le propriétaire spécifié par son nom")
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Taux annuel récupérée avec succès"),
             @ApiResponse(responseCode = "404", description = "Propriétaire non trouvé"),
             @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
     })
     @GetMapping("taux/{nom}")
     public ResponseEntity<Double> getTauxLocationAnnuel(
             @Parameter(description = "Le nom du propriétaire dont le taux veut être calculé", required = true)
             @PathVariable String nom) {
          double result = service.tauxLocationAnnuel(nom);
          if(result == -1){
               return ResponseEntity.notFound().build();
          }
          return ResponseEntity.ok(result);
     }

}
