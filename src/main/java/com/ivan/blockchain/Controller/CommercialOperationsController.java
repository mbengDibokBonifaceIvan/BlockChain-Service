package com.ivan.blockchain.Controller;

import com.ivan.blockchain.Service.CommercialOperationsService;
import com.ivan.blockchain.SmartContracts.CommercialOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;

@RestController
@RequestMapping("/api/operations")
public class CommercialOperationsController {
    @Autowired
    private CommercialOperationsService service;

    @PostMapping
    public ResponseEntity<Void> createOperation(
            @RequestParam BigInteger id,
            @RequestParam String type,
            @RequestParam String status,
            @RequestParam String seller,
            @RequestParam String buyer
    ) {
        service.createOperation(id, type, status, seller, buyer);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommercialOperations.Operation> getOperation(@PathVariable BigInteger id) {
        return ResponseEntity.ok(service.getOperation(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable BigInteger id,
            @RequestParam String newStatus
    ) {
        service.updateOperationStatus(id, newStatus);
        return ResponseEntity.ok().build();
    }
}