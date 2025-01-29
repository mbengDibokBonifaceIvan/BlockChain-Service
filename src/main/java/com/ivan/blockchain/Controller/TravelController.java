package com.ivan.blockchain.Controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.blockchain.Service.TravelService;
import com.ivan.blockchain.SmartContracts.Travel_Resource;
import com.ivan.blockchain.util.ResourceStateRequest;

@RestController
@RequestMapping("/api/travel")
public class TravelController {

     @Autowired
     private TravelService travelService;


    @PostMapping("/addResourceState")
    public ResponseEntity<String> addResourceState(@RequestBody ResourceStateRequest request) {
        try {
            travelService.addResourceState(request);
            return ResponseEntity.ok("Resource state added successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding resource state: " + e.getMessage());
        }
    }

    @GetMapping("/states/current/{identification}")
    public Travel_Resource.ResourceState getResourceState(@PathVariable BigInteger identification) {
        try {
            return travelService.getResourceState(identification);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/states/{identification}")
    public List<Travel_Resource.ResourceStateCreatedEventResponse> getResourceStates(@PathVariable BigInteger identification) {
        try {
            return travelService.getResourceStates(identification);
        } catch (Exception e) {
            return null;
        }
    }
    
}
