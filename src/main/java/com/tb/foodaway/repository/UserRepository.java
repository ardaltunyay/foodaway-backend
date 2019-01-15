package com.tb.foodaway.repository;

import com.tb.foodaway.model.enums.UserRole;
import com.tb.foodaway.model.persistance.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    List<User> findAllByRole(UserRole role);
}
