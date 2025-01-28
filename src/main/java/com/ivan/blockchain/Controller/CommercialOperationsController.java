package com.ivan.blockchain.Controller;

import com.ivan.blockchain.Service.CommercialOperationsService;
import com.ivan.blockchain.SmartContracts.CommercialOperations;
import com.ivan.blockchain.util.CommercialOperationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;

@RestController
@RequestMapping("/api/operations")
public class CommercialOperationsController {
    @Autowired
    private CommercialOperationsService service;

    @PostMapping("/create")
    public ResponseEntity<Void> createOperation(
            @RequestBody CommercialOperationDTO commercialOperationDTO
    ) throws Exception {
        service.createOperation(commercialOperationDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommercialOperationDTO> getOperation(@PathVariable BigInteger id) throws Exception {
        return ResponseEntity.ok(service.getOperation(id));
    }
//
//    @PutMapping("/{id}/status")
//    public ResponseEntity<Void> updateStatus(
//            @PathVariable BigInteger id,
//            @RequestParam String newStatus
//    ) {
//        service.updateOperationStatus(id, newStatus);
//        return ResponseEntity.ok().build();
//    }
}