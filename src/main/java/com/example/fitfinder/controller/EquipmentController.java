package com.example.fitfinder.controller;

import com.example.fitfinder.models.Equipment;
import com.example.fitfinder.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    // Functionality: Gets all equipment
    // Path: http://localhost:8080/api/equipment
    /**
     * Retrieves all equipment.
     *
     * @return List of all equipment
     */
    @GetMapping(path = "/equipment")
    public List<Equipment> getAllEquipment(){
        return equipmentService.getAllEquipment();
    }

    // Functionality: Gets all equipment for a gym
    // Path: http://localhost:8080/api/gyms/{gymId}/equipment
    /**
     * Retrieves all equipment for a specific gym.
     *
     * @param gymId ID of the gym
     * @return List of equipment for the specified gym
     */
    @GetMapping(path = "/gyms/{gymId}/equipment")
    public List<Equipment> getAllEquipmentByGymId(@PathVariable Long gymId) {
        return equipmentService.getAllEquipmentByGymId(gymId);
    }

    // Functionality: Creates a piece of equipment for gym
    // Path: http://localhost:8080/api/gyms/{gymId}/equipment
    /**
     * Creates a new piece of equipment for a specific gym.
     *
     * @param gymId ID of the gym
     * @param equipmentObject Equipment object to be created
     * @return The created equipment
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/gyms/{gymId}/equipment")
    public Equipment createEquipmentByGymId(@PathVariable Long gymId, @RequestBody Equipment equipmentObject){
        return equipmentService.createEquipmentByGymId(gymId, equipmentObject);
    }

    // Functionality: Updates a piece of equipment details for gym
    // Path: http://localhost:8080/api/gyms/{gymId}/equipment/{equipmentId}
    /**
     * Updates the details of an existing piece of equipment for a specific gym.
     *
     * @param gymId ID of the gym
     * @param equipmentId ID of the equipment
     * @param equipmentObject Updated Equipment object
     * @return The updated equipment
     */
    @PutMapping(path = "/gyms/{gymId}/equipment/{equipmentId}")
    public Equipment updateEquipmentByGymId(@PathVariable Long gymId, @PathVariable Long equipmentId, @RequestBody Equipment equipmentObject){
        return equipmentService.updateEquipmentByGymId(gymId, equipmentId, equipmentObject);
    }

    // Functionality: Deletes a piece of equipment for gym
    // Path: http://localhost:8080/api/gyms/{gymId}/equipment/{equipmentId}
    /**
     * Deletes an existing piece of equipment for a specific gym.
     *
     * @param gymId ID of the gym
     * @param equipmentId ID of the equipment to be deleted
     * @return The deleted equipment
     */
    @DeleteMapping(path = "/gyms/{gymId}/equipment/{equipmentId}")
    public Equipment deleteEquipmentByGymId(@PathVariable Long gymId, @PathVariable Long equipmentId){
        return equipmentService.deleteEquipmentByGymId(gymId, equipmentId);
    }
}
