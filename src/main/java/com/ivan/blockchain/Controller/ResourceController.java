package com.ivan.blockchain.Controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.blockchain.Service.ResourceService;
import com.ivan.blockchain.SmartContracts.Resource.ResourceData;
import com.ivan.blockchain.util.ResourceDTO;
import com.ivan.blockchain.util.ResourceResponseDTO;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/create")
    public ResponseEntity<Void> createResource(@RequestBody ResourceDTO resourceDTO) throws Exception {
        resourceService.createResource(resourceDTO);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponseDTO> getResource(@PathVariable BigInteger id) throws Exception {
        return ResponseEntity.ok(resourceService.getResource(id));
    }
}
