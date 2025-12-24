package com.mycompany.property_management.service;

import com.mycompany.property_management.model.UserDTO;

public interface UsersService {
        UserDTO register(UserDTO userDTO);
        UserDTO login(String email, String password);
}
