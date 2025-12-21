package com.fitness.userservice.service;


import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.entity.User;
import com.fitness.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public UserResponse registerUser(RegisterRequest request){
        if(userRepo.existsByEmail(request.getEmail())){
            throw new RuntimeException("User already exists with given email id.");
        }

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());

        User savedUser = userRepo.save(newUser);

        UserResponse user = new UserResponse();

        user.setId(savedUser.getId());
        user.setEmail(savedUser.getEmail());
        user.setFirstName(savedUser.getFirstName());
        user.setLastName(savedUser.getLastName());
        user.setCreatedAt(savedUser.getCreatedAt());
        user.setModifiedAt(savedUser.getModifiedAt());

        return user;
    }

    public UserResponse getUserDetails(String userId) {
        User requestedUser = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found!"));
        UserResponse user = new UserResponse();

        user.setId(requestedUser.getId());
        user.setEmail(requestedUser.getEmail());
        user.setFirstName(requestedUser.getFirstName());
        user.setLastName(requestedUser.getLastName());
        user.setCreatedAt(requestedUser.getCreatedAt());
        user.setModifiedAt(requestedUser.getModifiedAt());

        return user;
    }

    public Boolean validateUser(String userId) {
        return userRepo.existsById(userId);
    }
}
