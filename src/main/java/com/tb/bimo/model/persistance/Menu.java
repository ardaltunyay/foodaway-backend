package com.tb.bimo.model.persistance;

import com.tb.bimo.model.dto.common.SubMenuWithProductId;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Menu {

    @Id
    private String id;

    @NotNull
    private String companyId;

    @NotNull
    private String companyName;

    private String companyLogoUrl;

    private String branchId;

    private List<SubMenuWithProductId> subMenuList;
}
