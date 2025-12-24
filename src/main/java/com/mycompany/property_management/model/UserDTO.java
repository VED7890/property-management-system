package com.mycompany.property_management.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String ownerName;
    private String ownerEmail;
    private String phone;
    private String password;
}
