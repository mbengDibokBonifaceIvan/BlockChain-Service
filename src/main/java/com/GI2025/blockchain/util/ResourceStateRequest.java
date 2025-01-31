package com.GI2025.blockchain.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "RequestBody to add a new Resource State")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceStateRequest {

    @Schema(description = "Identifiant unique de la ressource", example = "123456789", required = true)
    private BigInteger identification = BigInteger.valueOf(0);

    @Schema(description = "Timestamp de création de la ressource", example = "1706558000", required = true)
    private BigInteger creationTimestamp = BigInteger.valueOf(0);

    @Schema(description = "Identifiant du créateur dans la plateforme", example = "742d35Cc6634C0532925a3b844Bc454e4438f44e", required = true)
    private String creator = "";

    @Schema(description = "Lieu de création de la ressource", example = "Yaounde", required = true)
    private String creationLocation = "";

    @Schema(description = "Localisation actuelle de la ressource", example = "Douala", required = true)
    private String location = "";

    @Schema(description = "Identifiant du propriétaire actuel dans la plateforme", example = "742d35Cc6634C0532925a3b844Bc454e4438f44e", required = true)
    private String owner = "";

    @Schema(description = "Timestamp de la dernière mise à jour", example = "1706558000", required = true)
    private BigInteger timestamp = BigInteger.valueOf(0);

    @Schema(description = "Données additionnelles", example = "data1,data2,data3")
    private String data = "";
}