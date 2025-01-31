package com.ivan.blockchain.controller;

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

import com.ivan.blockchain.service.CommercialOperationsServiceV2;
import com.ivan.blockchain.util.OperationStateRequest;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/v2/operations")
@Tag(name = "Commercial Operations", description = "API pour gérer les opérations commerciales")
public class CommercialOperationsControllerV2 {

    @Autowired
    private CommercialOperationsServiceV2 commercialOperationsService;

    @Operation(summary = "Ajouter un nouvel état d'opération", description = "Ajoute une nouvelle opération commerciale dans la blockchain")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Opération ajoutée avec succès"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur lors de l'ajout de l'opération")
    })
    @PostMapping
    public ResponseEntity<String> addOperationState(@RequestBody @Valid OperationStateRequest request) {
        try {
            commercialOperationsService.addOperationState(request.getIdentification(),
                    request.getType(),
                    request.getStatus(),
                    request.getProducer(),
                    request.getConsumer(),
                    request.getTimestamp(),
                    request.getData());
            return ResponseEntity.ok("Operation state added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error adding operation state: " + e.getMessage());
        }
    }

    @Operation(summary = "Obtenir les états d'une opération", description = "Récupère l'historique des états d'une opération commerciale")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des états trouvée", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OperationStateRequest.class))))
    })
    @GetMapping("/states/{identification}")
    public List<OperationStateRequest> getOperationStates(
            @Parameter(description = "Identifiant unique de l'opération") @PathVariable BigInteger identification) {
        try {
            return commercialOperationsService.getOperationStates(identification)
                    .stream()
                    .map(state -> {
                        return new OperationStateRequest(
                                state.identification,
                                state.operationType,
                                state.status,
                                state.producer,
                                state.consumer,
                                state.timestamp,
                                state.data);
                    }).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
