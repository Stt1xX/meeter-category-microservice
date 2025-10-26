package org.meeter.categories.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.meeter.categories.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "admin-api-categories", description = "As far as I understand it's only for admin api")
@RequestMapping("/admin/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Operation(description = "Adding new category by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category was added successfully"),
            @ApiResponse(responseCode = "400", description = "Category with this name already exist (or other errors?)")
    })
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
    @Operation(description = "Delete new category by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category was deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Category not found")
    })
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
    @Operation(description = "Delete new category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category was deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Category not found")
    })
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
