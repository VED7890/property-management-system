package com.mycompany.property_management.service.IMPL;

import com.mycompany.property_management.Converter.UserConverter;
import com.mycompany.property_management.Entity.UserEntity;
import com.mycompany.property_management.Repository.UserRepository;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.exception.ErrorModel;
import com.mycompany.property_management.model.UserDTO;
import com.mycompany.property_management.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> Oe = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
         if(Oe.isPresent()){
             List<ErrorModel> errorModelList = new ArrayList<>();
             ErrorModel errorModel = new ErrorModel();
             errorModel.setCode("EMAIL_ALREADY_REGISTER");
             errorModel.setMessage("Email Already Exist");
             errorModelList.add(errorModel);
             throw new BusinessException(errorModelList);
         }

         UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
         userEntity = userRepository.save(userEntity);
         userDTO = userConverter.convertEntityDTO(userEntity);

         return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);


        if(optionalUserEntity.isPresent()){
         userDTO = userConverter.convertEntityDTO(optionalUserEntity.get());
        }else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect Email or Password");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
         }
        return userDTO;
    }
}
