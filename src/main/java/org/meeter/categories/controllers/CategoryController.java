package org.meeter.categories.controllers;

import org.meeter.categories.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody String categoryName) {
        try {
            categoryService.addCategory(categoryName);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/name")
    public ResponseEntity<?> deleteCategoryByName(@RequestBody String categoryName) {
        try{
            categoryService.deleteCategory(categoryName);
        } catch(Exception e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/id")
    public ResponseEntity<?> deleteCategoryById(@RequestBody Integer categoryId) {
        try{
            categoryService.deleteCategory(categoryId);
        } catch(Exception e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
