package za.co.spvip.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.spvip.book.entity.Category;
import za.co.spvip.book.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private  CategoryRepository categoryRepository;

    // Get all categories
    @GetMapping
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
    //Add New Category
    @PostMapping
    public String addCategory(@RequestBody Category category){
        if (categoryRepository.findByName(category.getName()) != null) {
            return "Category already exists";
        }
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setDescription(category.getDescription());
        categoryRepository.save(newCategory);
        return "Category Added";
    }
    //Get Category by Id
    @GetMapping("{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    //TODO: Delete Category by Id

}
