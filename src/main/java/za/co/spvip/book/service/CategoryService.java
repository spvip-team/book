package za.co.spvip.book.service;

import za.co.spvip.book.entity.Book;
import za.co.spvip.book.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    String save(Category category);
    Category findById(Long id);
    String delete(Long id);
}
