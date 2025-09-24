package fac.luminy.m2.aa1.tp1.controller;

import fac.luminy.m2.aa1.tp1.model.dto.VoitureDTO;
import fac.luminy.m2.aa1.tp1.service.VoitureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Contrôleur REST pour gérer les opérations liées aux voitures.
 * Fournit des points d'accès pour récupérer les informations des voitures
 * en fonction du nom du propriétaire.
 *
 * @author matmiche
 */
@RestController
@RequestMapping("voitures")
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
     * Récupère
     */
}
