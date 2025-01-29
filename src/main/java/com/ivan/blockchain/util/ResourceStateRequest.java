package com.ivan.blockchain.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor@Schema(description = "Requête pour ajouter un nouvel état de ressource")
public class ResourceStateRequest {
    @Schema(description = "Identifiant unique de la ressource", example = "123456789")
    @NotNull
    private BigInteger identification;

    @Schema(description = "Timestamp de création", example = "1706558000")
    @NotNull
    private BigInteger creationTimestamp;

    @Schema(description = "Adresse Ethereum du créateur", 
           example = "0x742d35Cc6634C0532925a3b844Bc454e4438f44e")
    @NotNull
    private String creator;

    @Schema(description = "Lieu de création", example = "Paris")
    @NotNull
    private String birthPlace;

    @Schema(description = "Localisation actuelle", example = "Lyon")
    @NotNull
    private String location;

    @Schema(description = "Adresse Ethereum du propriétaire", 
           example = "0x742d35Cc6634C0532925a3b844Bc454e4438f44e")
    @NotNull
    private String owner;

    @Schema(description = "Timestamp de la dernière mise à jour", example = "1706558000")
    @NotNull
    private BigInteger timestamp;

}
