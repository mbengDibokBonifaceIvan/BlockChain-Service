package com.ivan.blockchain.Controller;



//import com.example.demo.service.ShipmentService;
import com.ivan.blockchain.Service.ShipmentService;
import com.ivan.blockchain.util.ShipmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/create")
    public String createShipment(@RequestParam String receiver, @RequestParam long departureDate, @RequestParam int packageWeight, @RequestParam String depot, @RequestParam String receiverHash) {
        try {
            shipmentService.createShipment(receiver, departureDate, packageWeight, depot, receiverHash);
            return "Shipment created successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error creating shipment: " + e.getMessage();
        }
    }

    @GetMapping("/retrieve")
    public ShipmentDetails getShipment(@RequestParam String senderAddress) {
        try {
            return shipmentService.getShipment(senderAddress);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
