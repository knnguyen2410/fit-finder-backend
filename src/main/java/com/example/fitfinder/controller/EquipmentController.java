package com.example.fitfinder.controller;

import com.example.fitfinder.models.Equipment;
import com.example.fitfinder.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
