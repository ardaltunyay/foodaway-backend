package com.tb.foodaway.model.dto.request;

import com.tb.foodaway.model.dto.common.SubMenuWithProductId;
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
