package com.tb.bimo.service;

import com.tb.bimo.exception.ResourceAlreadyExistsException;
import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.persistance.User;
import com.tb.bimo.model.dto.request.CreateUserRequest;
import com.tb.bimo.model.dto.mapper.UserMapper;
import com.tb.bimo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private final BCryptPasswordEncoder passwordEncoder;

    public void create(CreateUserRequest createUserRequest) {
        if (!userRepository.existsByEmail(createUserRequest.getEmail())) {

            User user = userMapper.createUserRequestToUser(createUserRequest);
            user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

            userRepository.save(user);
        } else {
            throw new ResourceAlreadyExistsException("Email address is already registered");
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    public String getAuthorizedBranchOfRestaurantUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email)).getAuthorizedBranch();
    }
}
