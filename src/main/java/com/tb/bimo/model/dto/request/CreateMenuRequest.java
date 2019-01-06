package com.tb.bimo.model.dto.request;

import com.tb.bimo.model.dto.common.SubMenuWithProductId;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuRequest {

    private String branchId;
    private List<SubMenuWithProductId> subMenuList;
}
