package com.mycompany.property_management.Converter;

import com.mycompany.property_management.Entity.PropertyEntity;
import com.mycompany.property_management.model.PropertyDTO;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity convertDTOtoEntity(PropertyDTO propertyDTO){
        PropertyEntity pe = new PropertyEntity();
        if (propertyDTO.getId() != null) {
            pe.setId(propertyDTO.getId());
        }

        pe.setTitle(propertyDTO.getTitle());
        pe.setAddress(propertyDTO.getAddress());
        pe.setPrice(propertyDTO.getPrice());
        pe.setDescription(propertyDTO.getDescription());

        return pe;
    }

    public PropertyDTO convertEntityDTO(PropertyEntity propertyEntity){
        PropertyDTO pd = new PropertyDTO();
        pd.setId(propertyEntity.getId());   // copy id back
        pd.setTitle(propertyEntity.getTitle());
        pd.setAddress(propertyEntity.getAddress());
        pd.setPrice(propertyEntity.getPrice());
        pd.setDescription(propertyEntity.getDescription());

        return pd;
    }
}
