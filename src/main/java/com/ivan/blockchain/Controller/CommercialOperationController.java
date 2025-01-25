package com.ivan.blockchain.Controller;

import com.ivan.blockchain.Service.CommercialOperationService;
import com.ivan.blockchain.util.CommercialOperationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/operations")
public class CommercialOperationController {


    private final CommercialOperationService service;

    @Autowired
    public CommercialOperationController(CommercialOperationService service) {
        this.service = service;
    }

    @PostMapping("/post")
    public ResponseEntity<CommercialOperationDTO> createOperation(@RequestBody CommercialOperationDTO dto) {
        try {
            CommercialOperationDTO createdOperation = service.createOperation(dto);
            return ResponseEntity.ok(createdOperation);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommercialOperationDTO> getOperation(@PathVariable BigInteger id) {
        try {
            CommercialOperationDTO operation = service.getOperation(id);
            return ResponseEntity.ok(operation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/ids")
    public ResponseEntity<List<BigInteger>> getAllOperationIds() {
        try {
            return ResponseEntity.ok(service.getAllOperationIds());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateOperationStatus(
        @PathVariable BigInteger id, 
        @RequestParam String newStatus
    ) {
        try {
            service.updateOperationStatus(id, newStatus);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/block-info")
    public ResponseEntity<Tuple2<BigInteger, BigInteger>> getOperationBlockInfo(@PathVariable BigInteger id) {
        try {
            return ResponseEntity.ok(service.getOperationBlockInfo(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}