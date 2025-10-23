package fac.luminy.m2.aa1.tp1.controller;

import fac.luminy.m2.aa1.tp1.model.TypeVoiture;
import fac.luminy.m2.aa1.tp1.model.dto.VoitureDTO;
import fac.luminy.m2.aa1.tp1.service.VoitureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Contrôleur REST pour gérer les opérations liées aux voitures.
 * Fournit des points d'accès pour récupérer les informations des voitures
 * en fonction du nom du propriétaire.
 *
 * @author matmiche
 */
@RestController
@RequestMapping("voitures/")
@Slf4j
@AllArgsConstructor
public class VoitureController {

    private VoitureService service;

    /**
     * Récupère la liste des voitures pour un propriétaire donné.
     *
     * @param nom le nom du propriétaire dont les voitures doivent être récupérées
     * @return une liste de {@link VoitureDTO} représentant les voitures du propriétaire
     */
    @Operation(summary = "Récupère la liste des voitures pour un propriétaire donné",
            description = "Retourne une liste de VoitureDTO pour le propriétaire spécifié par son nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des voitures récupérée avec succès"),
            @ApiResponse(responseCode = "404", description = "Propriétaire non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("proprietaire/{nom}")
    public List<VoitureDTO> getVoitures(
            @Parameter(description = "Le nom du propriétaire dont les voitures doivent être récupérées", required = true)
              @PathVariable String nom) {
        log.info("Controller - recuperation de voiture pour {}", nom);
        return service.recupererVoituresProprietaire(nom);
    }
    /**
     * Recherche les voitures selon les préférences du locataire
     *
     * @param types la liste de type de voiture voulu par le locataire
     * @param price le prix voulu de la voiture par le locataire
     * @return une liste de {@link VoitureDTO} représentant les voitures trouvé selon les filtres
     *      Si les deux filtres sont vides, alors nous retournons toutes les voitures disponibles
     */
    @Operation(summary = "Recherche une liste de voitures conformes aux filtres du locataire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des voitures filtrées récupérée avec succès"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping()
    public List<VoitureDTO> getVoituresWithFilter(
            @Parameter(description = "Liste des types voulu par le locataire")
            @RequestParam(required = false) List<TypeVoiture> types,
            @Parameter(description = "Prix voulu par le locataire")
            @RequestParam(required = false) Double price
    ) {
        return service.filtrageVoitures(price, types);
    }
}
