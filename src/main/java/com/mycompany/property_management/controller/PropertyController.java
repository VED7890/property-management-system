package com.mycompany.property_management.controller;

import com.mycompany.property_management.model.PropertyDTO;
import com.mycompany.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO){
        PropertyDTO saved = propertyService.saveProperty(propertyDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getProperties(){
        List<PropertyDTO> propertyList = propertyService.getProperties();
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

    @PutMapping("/properties/{propertyId}")   // use PUT for updates
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        PropertyDTO updated = propertyService.updateProperty(propertyDTO, propertyId);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId){
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }
}
