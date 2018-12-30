package com.tb.bimo.model.dto.mapper;

import com.tb.bimo.model.dto.request.CreateUserRequest;
import com.tb.bimo.model.persistance.User;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-12-30T15:19:45+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User createUserRequestToUser(CreateUserRequest createUserRequest) {
        if ( createUserRequest == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( createUserRequest.getEmail() );
        user.setPassword( createUserRequest.getPassword() );
        user.setRole( createUserRequest.getRole() );
        user.setEnabled( createUserRequest.isEnabled() );

        return user;
    }

    @Override
    public CreateUserRequest userToCreateUserRequest(User user) {
        if ( user == null ) {
            return null;
        }

        CreateUserRequest createUserRequest = new CreateUserRequest();

        createUserRequest.setEmail( user.getEmail() );
        createUserRequest.setPassword( user.getPassword() );
        createUserRequest.setRole( user.getRole() );
        createUserRequest.setEnabled( user.isEnabled() );

        return createUserRequest;
    }
}
