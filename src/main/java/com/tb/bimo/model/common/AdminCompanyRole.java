package com.tb.bimo.model.common;

import com.tb.bimo.model.enums.Status;
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
