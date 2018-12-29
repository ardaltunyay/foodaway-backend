package com.tb.bimo.controller.mobile;

import com.tb.bimo.exception.ResourceNotFoundException;
import com.tb.bimo.model.persistance.User;
import com.tb.bimo.model.dto.request.UserLoginRequest;
import com.tb.bimo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/auth")
public class AuthMobileController {

    private final UserService userService;

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void login(@Validated @RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        log.info("Trying to login with email {}", userLoginRequest.getEmail());

        try {
            request.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());
        } catch (ServletException e) {
            log.warn("Login with email {} has failed with message {}", userLoginRequest.getEmail(), Optional.ofNullable(e.getMessage()).orElse("null"));

            if (e.getCause() instanceof BadCredentialsException) {
                throw new ResourceNotFoundException("E-mail adresiniz yada şifreniz hatalıdır.");
            }
            throw e;
        }

        log.info("Login with email {} is successful.", userLoginRequest.getEmail());
    }

    @SneakyThrows
    @GetMapping("/user")
    public User getUserDetail(Authentication authentication) {
        return userService.getUserByEmail(authentication.getName());
    }
}
