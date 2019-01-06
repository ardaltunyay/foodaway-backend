package com.tb.bimo.model.persistance;

import com.tb.bimo.model.dto.common.SubMenuWithProductId;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Menu {
    private String companyId;
    private String companyName;
    private String companyLogoUrl;
    private String branchId;
    private List<SubMenuWithProductId> subMenuList;
}
