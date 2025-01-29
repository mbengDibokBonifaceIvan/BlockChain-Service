package com.ivan.blockchain.Controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.blockchain.Service.TravelService;
import com.ivan.blockchain.SmartContracts.Travel_Resource;
import com.ivan.blockchain.util.ResourceStateRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/travel")
@Tag(name = "Travel Controller", description = "API pour gérer les états des ressources de voyage")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @Operation(summary = "Ajouter un nouvel état de ressource", description = "Ajoute un nouvel état de ressource dans la blockchain")
     @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "État ajouté avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping("/addResourceState")
    public ResponseEntity<String> addResourceState(
            @RequestBody @Valid ResourceStateRequest request) {
        try {
            travelService.addResourceState(request);
            return ResponseEntity.ok("Resource state added successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding resource state: " + e.getMessage());
        }
    }

    @Operation(summary = "Obtenir l'état actuel d'une ressource", description = "Récupère l'état actuel d'une ressource à partir de son identification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "État trouvé", content = @Content(schema = @Schema(implementation = Travel_Resource.ResourceState.class))),
            @ApiResponse(responseCode = "404", description = "Ressource non trouvée")
    })
    @GetMapping("/states/current/{identification}")
    public ResponseEntity<Travel_Resource.ResourceState> getResourceState(
            @Parameter(description = "Identifiant unique de la ressource") @PathVariable BigInteger identification) {
        try {
            Travel_Resource.ResourceState state = travelService.getResourceState(identification);
            if (state == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(state);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtenir l'historique des états d'une ressource", description = "Récupère tous les états d'une ressource depuis sa création")
    @GetMapping("/states/{identification}")
    public ResponseEntity<List<Travel_Resource.ResourceStateCreatedEventResponse>> getResourceStates(
            @Parameter(description = "Identifiant unique de la ressource") @PathVariable BigInteger identification) {
        try {
            List<Travel_Resource.ResourceStateCreatedEventResponse> states = travelService
                    .getResourceStates(identification);
            if (states.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(states);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}