//package com.ivan.blockchain.Controller;
//
//
//import com.ivan.blockchain.Requests.ResourceRequest;
//import com.ivan.blockchain.Service.ResourceService;
//import com.ivan.blockchain.util.ResourceDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.math.BigInteger;
//
//@RestController
//@RequestMapping("/api/resources")
//public class ResourceController {
//
//    @Autowired
//    private ResourceService resourceService;
//
//    @PostMapping("/create")
//    public ResponseEntity<BigInteger> createResource(@RequestBody ResourceRequest request) throws Exception {
//        BigInteger resourceId = resourceService.createResource(request.getOriginLocation());
//        return ResponseEntity.ok(resourceId);
//    }
//
//    @GetMapping("/{resourceId}")
//    public ResponseEntity<ResourceDTO> getResource(@PathVariable BigInteger resourceId) throws Exception {
//        ResourceDTO resource = resourceService.getResource(resourceId);
//        return ResponseEntity.ok(resource);
//    }
//
//    @PutMapping("/{resourceId}/location")
//    public ResponseEntity<Void> updateLocation(
//            @PathVariable BigInteger resourceId,
//            @RequestBody String newLocation) throws Exception {
//        resourceService.updateLocation(resourceId, newLocation);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/{resourceId}/owner")
//    public ResponseEntity<Void> transferOwnership(
//            @PathVariable BigInteger resourceId,
//            @RequestBody String newOwner) throws Exception {
//        resourceService.transferOwnership(resourceId, newOwner);
//        return ResponseEntity.ok().build();
//    }
//}
