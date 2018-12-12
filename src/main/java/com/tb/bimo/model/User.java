package com.tb.bimo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tb.bimo.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

    @Id
    private String id;

    private String email;

    @JsonIgnore
    private String password;

    private UserRole role;

    private boolean isEnabled;
}
