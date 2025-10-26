package org.meeter.categories.services;

import jakarta.transaction.Transactional;
import org.meeter.categories.entities.Category;
import org.meeter.categories.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void addCategory(String name){
        categoryRepository.save(new Category(name));
    }

    @Transactional
    public void deleteCategory(String name){
        categoryRepository.findByName(name).orElseThrow(()->new RuntimeException("Category not found"));
        categoryRepository.deleteByName(name);
    }

    @Transactional
    public void deleteCategory(Integer id){
        categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category not found"));
        categoryRepository.deleteById(id);
    }
}
