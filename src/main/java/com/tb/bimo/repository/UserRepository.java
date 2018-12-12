package com.tb.bimo.repository;

import com.tb.bimo.model.User;
import com.tb.bimo.model.enums.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    List<User> findAllByRole(UserRole role);
}
