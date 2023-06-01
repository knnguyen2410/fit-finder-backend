package com.example.fitfinder.controller;

import com.example.fitfinder.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
}
