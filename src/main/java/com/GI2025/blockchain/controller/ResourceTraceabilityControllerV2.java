package com.GI2025.blockchain.controller;

import com.GI2025.blockchain.service.ResourceTraceabilityServiceV2;
import com.GI2025.blockchain.util.ResourceStateRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/v2/resource")
@Tag(name = "Resource Traceability", description = "API qui gère la traçabilité des ressources")
public class ResourceTraceabilityControllerV2 {

    @Autowired
    private ResourceTraceabilityServiceV2 resourceTraceabilityService;

    @Operation(summary = "Ajouter un nouvel état d'une ressource", description = "Pour une Ressource identifiée par un identifiant dans la plateforme, on peut ajouter son nouvel état.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "État de la ressource ajouté avec succès !"),
            @ApiResponse(responseCode = "500", description = "Erreur lors de l'ajout de l'état de la ressource")
    })
    @PostMapping
    public ResponseEntity<String> addResourceState(
            @RequestBody @Valid ResourceStateRequest request) {
        try {
            resourceTraceabilityService.addResourceState(request);
            return ResponseEntity.ok("État de la ressource ajouté avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erreur lors de l'ajout de l'état de la ressource: " + e.getMessage());
        }
    }

    @Operation(summary = "Obtenir l'historique des états d'une ressource", description = "Pour un identifiant donné, obtenez tous les états enregistrés dans la blockchain pour cette ressource")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historique des États enregistré sur la blockchain", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResourceStateRequest.class))))
    })
    @GetMapping("/states/{identification}")
    public List<ResourceStateRequest> getResourceStates(
            @Parameter(description = "Identifiant unique de la ressource") @PathVariable BigInteger identification) {
        try {
            return resourceTraceabilityService.getResourceStates(identification)
                    .stream()
                    .map(state -> {
                        return new ResourceStateRequest(
                                state.identification,
                                state.creationTimestamp,
                                state.creator,
                                state.creationLocation,
                                state.location,
                                state.owner,
                                state.timestamp,
                                state.data);
                    })
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
