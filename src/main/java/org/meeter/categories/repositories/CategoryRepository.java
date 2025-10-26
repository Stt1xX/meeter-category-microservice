package org.meeter.categories.repositories;

import org.meeter.categories.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    void deleteByName(String name);
    Optional<Category> findByName(String name);
}
