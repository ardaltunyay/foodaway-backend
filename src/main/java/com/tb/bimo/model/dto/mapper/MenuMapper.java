package com.tb.bimo.model.dto.mapper;

import com.tb.bimo.model.dto.request.CreateMenuRequest;
import com.tb.bimo.model.persistance.Menu;
import org.mapstruct.Mapper;

@Mapper
public interface MenuMapper {

    Menu createMenuRequestToMenu(CreateMenuRequest createMenuRequest);
}
