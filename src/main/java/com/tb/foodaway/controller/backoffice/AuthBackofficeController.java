package com.tb.foodaway.controller.backoffice;

import com.tb.foodaway.exception.ResourceNotFoundException;
import com.tb.foodaway.model.dto.request.UserLoginRequest;
import com.tb.foodaway.model.enums.UserRole;
import com.tb.foodaway.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/backoffice/auth")
public class AuthBackofficeController {

    private final UserService userService;

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void login(@Validated @RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        log.info("Trying to login with email {}", userLoginRequest.getEmail());

        try {
            if (userService.getUserByEmail(userLoginRequest.getEmail()).getRole() == UserRole.CONTENT_MANAGER) {
                request.getSession().setMaxInactiveInterval(8553600); //99 days of token expire time
                request.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());
            } else {
                throw new ResourceNotFoundException("E-mail adresiniz yada şifreniz hatalıdır.");
            }
        } catch (ServletException e) {
            log.warn("Login with email {} has failed with message {}", userLoginRequest.getEmail(), Optional.ofNullable(e.getMessage()).orElse("null"));

            if (e.getCause() instanceof BadCredentialsException) {
                throw new ResourceNotFoundException("E-mail adresiniz yada şifreniz hatalıdır.");
            }
            throw e;
        }

        log.info("Login with email {} is successful.", userLoginRequest.getEmail());
    }
}
