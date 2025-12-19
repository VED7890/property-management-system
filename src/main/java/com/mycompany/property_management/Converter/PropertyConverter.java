package com.mycompany.property_management.Converter;

import com.mycompany.property_management.Entity.PropertyEntity;
import com.mycompany.property_management.model.PropertyDTO;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity convertDTOtoEntity(PropertyDTO propertyDTO){
        PropertyEntity pe = new PropertyEntity();

        // If DTO contains id (for update), set it into entity
        if (propertyDTO.getId() != null) {
            pe.setId(propertyDTO.getId());
        }

        pe.setTitle(propertyDTO.getTitle());
        pe.setAddress(propertyDTO.getAddress());
        pe.setOwnerName(propertyDTO.getOwnerName());
        pe.setOwnerEmail(propertyDTO.getOwnerEmail());
        pe.setPrice(propertyDTO.getPrice());
        pe.setDescription(propertyDTO.getDescription());

        return pe;
    }

    public PropertyDTO convertEntityDTO(PropertyEntity propertyEntity){
        PropertyDTO pd = new PropertyDTO();
        pd.setId(propertyEntity.getId());   // copy id back
        pd.setTitle(propertyEntity.getTitle());
        pd.setAddress(propertyEntity.getAddress());
        pd.setOwnerName(propertyEntity.getOwnerName());
        pd.setOwnerEmail(propertyEntity.getOwnerEmail());
        pd.setPrice(propertyEntity.getPrice());
        pd.setDescription(propertyEntity.getDescription());

        return pd;
    }
}
