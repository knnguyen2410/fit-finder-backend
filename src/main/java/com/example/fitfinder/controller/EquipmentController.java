package com.example.fitfinder.controller;

import com.example.fitfinder.models.Equipment;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/gyms/{gymId}/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping(path = "")
    public List<Equipment> getAllEquipmentByGymId(@PathVariable Long gymId) {
        return equipmentService.getAllEquipmentByGymId(gymId);
    }

    // Functionality: Creates a piece of equipment for gym
    // Path: http://localhost:8080/api/gyms/{gymId}/equipment
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public Equipment createEquipmentByGymId(@PathVariable Long gymId, @RequestBody Equipment equipmentObject){
        return equipmentService.createEquipmentByGymId(gymId, equipmentObject);
    }

    // Functionality: Updates a piece of equipment details for gym
    // Path: http://localhost:8080/api/gyms/{gymId}/equipment/{equipmentId}
    @PutMapping(path = "/{equipmentId}")
    public Equipment updateEquipmentByGymId(@PathVariable Long gymId, @PathVariable Long equipmentId, @RequestBody Equipment equipmentObject){
        return equipmentService.updateEquipmentByGymId(gymId, equipmentId, equipmentObject);
    }
}
