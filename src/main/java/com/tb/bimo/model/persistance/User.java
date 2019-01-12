package com.tb.bimo.model.persistance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tb.bimo.model.common.AdminCompanyRole;
import com.tb.bimo.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {

    @Id
    private String id;

    @NotNull
    private String email;

    @JsonIgnore
    @NotNull
    private String password;

    @NotNull
    private UserRole role;

    @NotNull
    private boolean isEnabled;

    @JsonIgnore
    private List<AdminCompanyRole> adminCompanyRoles;

    @JsonIgnore
    private List<AdminCompanyRole> authorizedBranches;
}
