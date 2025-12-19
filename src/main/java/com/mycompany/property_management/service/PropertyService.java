package com.mycompany.property_management.service;

import com.mycompany.property_management.model.PropertyDTO;
import java.util.List;

public interface PropertyService  {

    PropertyDTO saveProperty(PropertyDTO propertyDTO);
    List<PropertyDTO> getProperties();
    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);
    void deleteProperty(Long propertyId);
}
