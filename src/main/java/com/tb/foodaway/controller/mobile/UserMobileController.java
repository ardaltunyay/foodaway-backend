package com.tb.foodaway.controller.mobile;

import com.tb.foodaway.model.dto.request.CreateUserRequest;
import com.tb.foodaway.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/user")
public class UserMobileController {

    private final UserService userService;

    @PostMapping
    public void addUser(@RequestBody CreateUserRequest createUserRequest) {

        log.info("Adding user with email: {}", createUserRequest.getEmail());
        userService.create(createUserRequest);
    }
}
