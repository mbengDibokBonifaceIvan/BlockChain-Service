package com.GI2025.blockchain.service;

import com.GI2025.blockchain.contracts.javaWrapper.CommercialOperations;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Commercial Operations", description = "API pour la gestion des opérations commerciales sur la blockchain")
@Service
public class CommercialOperationsServiceV2 {

    private final Web3j web3j;
    private final TransactionManager transactionManager;

    public CommercialOperationsServiceV2(Web3j web3j, TransactionManager transactionManager) {
        this.web3j = web3j;
        this.transactionManager = transactionManager;
    }

    @Operation(summary = "Ajouter un état d'opération", description = "Ajoute un nouvel état d'opération commerciale dans la blockchain")
    @ApiResponse(responseCode = "200", description = "État d'opération ajouté avec succès")
    @ApiResponse(responseCode = "500", description = "Erreur lors de l'ajout de l'état d'opération")
    public void addOperationState(
            @Parameter(description = "Identifiant unique de l'opération") BigInteger identification,
            @Parameter(description = "Type de l'opération") String type,
            @Parameter(description = "Statut de l'opération") String status,
            @Parameter(description = "Producteur de l'opération") String producer,
            @Parameter(description = "Consommateur de l'opération") String consumer,
            @Parameter(description = "Timestamp de l'opération") BigInteger timestamp,
            @Parameter(description = "Données additionnelles") String data) throws Exception {
        CommercialOperations contract = CommercialOperations.load(ContractService.getComOpsV2Address(), web3j,
                transactionManager, new DefaultGasProvider());
        contract.send_addOperationState(
                identification, type, status, producer, consumer, timestamp, data).send();
    }

    @SuppressWarnings("unchecked")
    @Operation(summary = "Obtenir les états d'une opération", description = "Récupère l'historique des états d'une opération commerciale")
    @ApiResponse(responseCode = "200", description = "États de l'opération récupérés avec succès", content = @Content(schema = @Schema(implementation = CommercialOperations.OperationState.class)))
    public List<CommercialOperations.OperationState> getOperationStates(
            @Parameter(description = "Identifiant de l'opération à rechercher") BigInteger identification)
            throws Exception {
        CommercialOperations contract = CommercialOperations.load(ContractService.getComOpsV2Address(), web3j,
                transactionManager, new DefaultGasProvider());
        return contract.call_getOperationStates(identification).send();
    }

    // public List<CommercialOperations.OperationStateCreatedEventResponse>
    // getOperationStates(BigInteger identification) throws Exception {
    // CommercialOperations contract =
    // CommercialOperations.load(ContractService.getComOpsAddress(), web3j,
    // transactionManager, new DefaultGasProvider());
    // BigInteger start = contract.call_deploymentBlockNumber().send();
    // BigInteger current = contract.call_getCurrentBlockNumber().send();
    // return contract.operationStateCreatedEventFlowable(new
    // DefaultBlockParameterNumber(start),new DefaultBlockParameterNumber(current))
    // .filter(event -> event.identification.equals(identification))
    // .toList()
    // .blockingGet();
    // }
}
