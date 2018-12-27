package com.tb.bimo.model.dto.mapper;

import com.tb.bimo.model.persistance.User;
import com.tb.bimo.model.dto.request.CreateUserRequest;
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
