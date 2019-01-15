package com.tb.foodaway.model.dto.mapper;

import com.tb.foodaway.model.dto.request.CreateUserRequest;
import com.tb.foodaway.model.persistance.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User createUserRequestToUser(CreateUserRequest createUserRequest);

    CreateUserRequest userToCreateUserRequest(User user);

    /*
    User updateUserRequestToUser(UpdateUserRequest updateUserRequest);
    UpdateUserRequest userToUpdateUserRequest(User user);
    */
}
