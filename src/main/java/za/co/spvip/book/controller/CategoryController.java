package za.co.spvip.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.spvip.book.entity.Category;
import za.co.spvip.book.repository.CategoryRepository;
import za.co.spvip.book.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Get all categories
    @GetMapping
    public List<Category> getAll()
    {
        // Call the service to get all categories
        return categoryService.findAll();
    }
    //Add New Category
    @PostMapping
    public String addCategory(@RequestBody Category category){
       return categoryService.save(category);
    }
    //Get Category by Id
    @GetMapping("{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    //Delete Category by Id
    @DeleteMapping("{id}")
    public String deleteCategoryById(@PathVariable Long id){
        return categoryService.delete(id);
    }

}
