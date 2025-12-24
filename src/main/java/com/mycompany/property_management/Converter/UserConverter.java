package com.mycompany.property_management.Converter;

import com.mycompany.property_management.Entity.UserEntity;
import com.mycompany.property_management.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertDTOtoEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setOwnerEmail(userDTO.getOwnerEmail());
        userEntity.setOwnerName(userDTO.getOwnerName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setPhone(userDTO.getPhone());
        return userEntity;
    }

    public UserDTO convertEntityDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setOwnerEmail(userEntity.getOwnerEmail());
        userDTO.setOwnerName(userEntity.getOwnerName());
        userDTO.setPhone(userEntity.getPhone());
        return userDTO;
    }
}
