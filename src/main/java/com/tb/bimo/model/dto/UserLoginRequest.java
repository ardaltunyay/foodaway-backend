package com.tb.bimo.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginRequest {

    @NotNull
    @Size(min = 2, max = 50, message = "Length of e-mail must be shorter than 50 and longer than 2 characters.")
    private String email;

    @NotNull
    @Size(min = 2, max = 50, message = "Length of password must be shorter than 50 and longer than 2 characters.")
    private String password;
}