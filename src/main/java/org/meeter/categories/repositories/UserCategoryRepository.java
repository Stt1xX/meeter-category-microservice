package org.meeter.categories.repositories;

import org.apache.catalina.User;
import org.meeter.categories.entities.UserCategory;
import org.meeter.categories.entities.UserCategoryId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserCategoryRepository extends CrudRepository<UserCategory, Integer> {
    List<UserCategory> findByIdUserId(UUID userID);
    void deleteById(UserCategoryId id);
    Optional<UserCategory> findById(UserCategoryId id);
}
