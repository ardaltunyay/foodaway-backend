package com.tb.bimo.model.dto.request;

import com.tb.bimo.model.enums.UserRole;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String email;

    private String password;

    private UserRole role;

    private boolean isEnabled;
}
