package com.ftd.auth.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findById(String id);
    User findByUserNameIgnoreCaseAndPassword(
            String userName, String password);
}
