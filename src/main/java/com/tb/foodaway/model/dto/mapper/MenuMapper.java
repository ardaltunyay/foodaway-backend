package com.tb.foodaway.model.dto.mapper;

import com.tb.foodaway.model.dto.request.CreateMenuRequest;
import com.tb.foodaway.model.persistance.Menu;
import org.mapstruct.Mapper;

@Mapper
public interface MenuMapper {

    Menu createMenuRequestToMenu(CreateMenuRequest createMenuRequest);
}
