package com.ivan.blockchain.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requête pour ajouter une nouvelle opération commerciale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationStateRequest {

    @Schema(description = "Identifiant unique de l'opération", example = "18", required = true)
    private BigInteger identification = BigInteger.valueOf(0);

    @Schema(description = "Type d'opération", example = "VENTE/ACHAT", required = true)
    private String type = "";

    @Schema(description = "Statut de l'opération", example = "EN_COURS", required = true)
    private String status = "";

    @Schema(description = "Identification du producteur dans la plateforme", example = "742d35Cc6634C0532925a3b844Bc454e4438f44e", required = true)
    private String producer = "";

    @Schema(description = "Identification du consommateur dans la plateforme", example = "742d35Cc6634C0532925a3b844Bc454e4438f44e", required = true)
    private String consumer = "";

    @Schema(description = "Timestamp de l'opération", example = "1706558000", required = true)
    private BigInteger timestamp = BigInteger.valueOf(0);

    @Schema(description = "Données additionnelles", example = "data1,data2,data3")
    private String data = "";
}
