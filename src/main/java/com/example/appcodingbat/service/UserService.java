package com.example.appcodingbat.service;

import com.example.appcodingbat.entity.User;
import com.example.appcodingbat.payload.ApiResponse;
import com.example.appcodingbat.payload.UserDto;
import com.example.appcodingbat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUserList(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public ApiResponse addUser(UserDto userDto){
        boolean existsByEmail = userRepository.existsByEmail(userDto.getEmail());
        if (existsByEmail)
            return new ApiResponse("User with this email already exist!",false);

        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
        return new ApiResponse("User added",true);
    }

    public ApiResponse editUser(UserDto userDto,Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("User with such id not found ",false);
        boolean existsByEmailAndIdNot = userRepository.existsByEmailAndIdNot(userDto.getEmail(), id);
        if (existsByEmailAndIdNot)
            return new ApiResponse("This email already exists",false);
        User editingUser = optionalUser.get();
        editingUser.setEmail(userDto.getEmail());
        editingUser.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(editingUser);
        return new ApiResponse("User edited", true);
    }

    public ApiResponse deleteUserById(Integer id){
        try {
             userRepository.deleteById(id);
             return new ApiResponse("User deleted",true);
        }catch (Exception e){
            return new ApiResponse("User with such id not found",false);
        }
    }
}
