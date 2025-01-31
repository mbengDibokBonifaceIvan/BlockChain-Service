package com.GI2025.blockchain.service;

import com.GI2025.blockchain.contracts.javaWrapper.ResourceTraceability;
import com.GI2025.blockchain.util.ResourceStateRequest;
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

@Tag(name = "Resource Traceability", description = "API pour la traçabilité des ressources sur la blockchain")
@Service
public class ResourceTraceabilityServiceV2 {

    private final Web3j web3j;
    private final TransactionManager transactionManager;

    public ResourceTraceabilityServiceV2(Web3j web3j, TransactionManager transactionManager) {
        this.web3j = web3j;
        this.transactionManager = transactionManager;
    }

    @Operation(summary = "Ajouter un état de ressource", description = "Ajoute un nouvel état de ressource dans la blockchain")
    @ApiResponse(responseCode = "200", description = "État de ressource ajouté avec succès")
    @ApiResponse(responseCode = "500", description = "Erreur lors de l'ajout de l'état de ressource")
    public void addResourceState(
            @Parameter(description = "Informations sur l'état de la ressource", required = true, schema = @Schema(implementation = ResourceStateRequest.class)) ResourceStateRequest request)
            throws Exception {
        ResourceTraceability contract = ResourceTraceability.load(ContractService.getResourceV2Address(), web3j,
                transactionManager, new DefaultGasProvider());
        contract.send_addResourceState(
                request.getIdentification(),
                request.getCreationTimestamp(),
                request.getCreator(),
                request.getCreationLocation(),
                request.getLocation(),
                request.getOwner(),
                request.getTimestamp(),
                request.getData()).send();
    }

    @SuppressWarnings("unchecked")
    @Operation(summary = "Obtenir les états d'une ressource", description = "Récupère l'historique des états d'une ressource")
    @ApiResponse(responseCode = "200", description = "États de la ressource récupérés avec succès", content = @Content(schema = @Schema(implementation = ResourceTraceability.ResourceState.class)))
    public List<ResourceTraceability.ResourceState> getResourceStates(
            @Parameter(description = "Identifiant de la ressource à rechercher") BigInteger identification)
            throws Exception {
        ResourceTraceability contract = ResourceTraceability.load(ContractService.getResourceV2Address(), web3j,
                transactionManager, new DefaultGasProvider());
        return contract.call_getResourceStates(identification).send();
    }

    // public List<ResourceTraceability.ResourceStateCreatedEventResponse>
    // getResourceStates(BigInteger identification) throws Exception {
    // ResourceTraceability contract =
    // ResourceTraceability.load(ContractService.getResourceAddress(), web3j,
    // transactionManager, new DefaultGasProvider());
    // BigInteger start = contract.call_deploymentBlockNumber().send();
    // BigInteger current = contract.call_getCurrentBlockNumber().send();
    // return contract.resourceStateCreatedEventFlowable(new
    // DefaultBlockParameterNumber(start),new DefaultBlockParameterNumber(current))
    // .filter(event -> event.identification.equals(identification))
    // .toList()
    // .blockingGet();
    // }
}
