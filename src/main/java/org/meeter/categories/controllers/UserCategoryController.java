package org.meeter.categories.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.meeter.categories.entities.Category;
import org.meeter.categories.entities.UserCategoryId;
import org.meeter.categories.services.UserCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "api-categories", description = "Main interface for crud operations with activity categories for users")
@RequestMapping("/api/categories")
public class UserCategoryController {
    private final UserCategoryService userCategoryService;

    public UserCategoryController(UserCategoryService userCategoryService) {
        this.userCategoryService = userCategoryService;
    }

    @GetMapping("/{usr}")
    @Operation(description = "Get user's categories by user's uuid (Always successful?)")
    public List<Category> getUsersCategories(@PathVariable UUID usr){
        return userCategoryService.getCategoriesForUser(usr);
    }

    @PostMapping
    @Operation(description = "Add new category to user by user's uuid and category's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record added successfully"),
            @ApiResponse(responseCode = "400", description = "Category not found or this pair [uuid, id] already exist")
    })
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
    @Operation(description = "Delete category from user by uuid and id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record was deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Category not found or this pair [uuid, id] not found")
    })
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
