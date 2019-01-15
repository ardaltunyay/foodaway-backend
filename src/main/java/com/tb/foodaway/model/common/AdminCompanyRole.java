package com.tb.foodaway.model.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class AdminCompanyRole {

    String companyId;
    String branchId;
    //Set<AdminRole> roles;
}
