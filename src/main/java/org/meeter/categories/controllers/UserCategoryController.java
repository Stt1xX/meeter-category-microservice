package org.meeter.categories.controllers;

import org.meeter.categories.entities.Category;
import org.meeter.categories.entities.UserCategoryId;
import org.meeter.categories.services.UserCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class UserCategoryController {
    private final UserCategoryService userCategoryService;

    public UserCategoryController(UserCategoryService userCategoryService) {
        this.userCategoryService = userCategoryService;
    }

    @GetMapping("/{usr}")
    public List<Category> getUsersCategories(@PathVariable UUID usr){
        return userCategoryService.getCategoriesForUser(usr);
    }

    @PostMapping
    public ResponseEntity<?> addCategoryToUser(@RequestBody UserCategoryId id){
        try {
            userCategoryService.addCategoryForUser(id);
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCategoryFromUser(@RequestBody UserCategoryId userCategory){
        try {
            userCategoryService.deleteCategoryForUser(userCategory);
        } catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
