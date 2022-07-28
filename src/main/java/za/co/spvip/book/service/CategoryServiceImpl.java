package za.co.spvip.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.spvip.book.entity.Book;
import za.co.spvip.book.entity.Category;
import za.co.spvip.book.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public String save(Category category) {
        if (categoryRepository.findByName(category.getName()) != null) {
            return "Category already exists";
        }
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setDescription(category.getDescription());
        categoryRepository.save(newCategory);
        return "Category Added";
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public String delete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return "Category Deleted";
        }
        return "Category does not exist";
    }


}
