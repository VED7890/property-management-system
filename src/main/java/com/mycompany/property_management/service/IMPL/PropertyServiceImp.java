package com.mycompany.property_management.service.IMPL;

import com.mycompany.property_management.Converter.PropertyConverter;
import com.mycompany.property_management.Entity.PropertyEntity;
import com.mycompany.property_management.Repository.PropertyRepository;
import com.mycompany.property_management.model.PropertyDTO;
import com.mycompany.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImp implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO){
        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        PropertyEntity saved = propertyRepository.save(pe);

        // convert saved entity -> dto (will include id)
        PropertyDTO dto = propertyConverter.convertEntityDTO(saved);
        return dto;
    }

    @Override
    public List<PropertyDTO> getProperties() {
        List<PropertyEntity> prop = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propList  = new ArrayList<>();
        for (PropertyEntity pe : prop){
            PropertyDTO dto = propertyConverter.convertEntityDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());

            // Save updated entity first (so DB-managed fields are applied)
            PropertyEntity saved = propertyRepository.save(pe);

            // Convert saved to DTO and return
            dto = propertyConverter.convertEntityDTO(saved);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);

    }
}
