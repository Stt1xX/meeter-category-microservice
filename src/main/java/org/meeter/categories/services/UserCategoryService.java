package org.meeter.categories.services;

import jakarta.transaction.Transactional;
import org.meeter.categories.entities.Category;
import org.meeter.categories.entities.UserCategory;
import org.meeter.categories.entities.UserCategoryId;
import org.meeter.categories.repositories.CategoryRepository;
import org.meeter.categories.repositories.UserCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserCategoryService {

    private final UserCategoryRepository userCategoryRepository;
    private final CategoryRepository categoryRepository;

    public UserCategoryService(UserCategoryRepository userCategoryRepository, CategoryRepository categoryRepository) {
        this.userCategoryRepository = userCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoriesForUser(UUID usr){
        return userCategoryRepository.findByIdUserId(usr)
                .stream()
                .map(UserCategory::getCategory)
                .toList();
    }

    @Transactional
    public void addCategoryForUser(UserCategoryId compositeId){
        Category category = categoryRepository.findById(compositeId.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found"));
        userCategoryRepository.save(new UserCategory(compositeId, category));
    }

    @Transactional
    public void deleteCategoryForUser(UserCategoryId compositeId){
        categoryRepository.findById(compositeId.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found"));
        userCategoryRepository.findById(compositeId)
                        .orElseThrow(() -> new RuntimeException(
                                String.format("User %s, have not category with id %d",
                                        compositeId.getUserId().toString(),
                                        compositeId.getCategoryId())));
        userCategoryRepository.deleteById(compositeId);
    }
}
