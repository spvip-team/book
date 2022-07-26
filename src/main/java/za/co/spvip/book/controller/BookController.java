package za.co.spvip.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.spvip.book.entity.Book;
import za.co.spvip.book.entity.Category;
import za.co.spvip.book.repository.BookRepository;
import za.co.spvip.book.repository.CategoryRepository;

import java.util.List;

@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    //Get all books
    @GetMapping
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    //Add book
    @PostMapping
    public String addBook(@RequestBody Book book){
        Category category = categoryRepository.findById(book.getCategory().getId()).orElse(null);
        //Check if category exists
        if (category == null) {
            return "Category does not exist";
        }
        //Create a new book
        Book newBook = new Book();
        newBook.setTitle(book.getTitle());
        newBook.setDescription(book.getDescription());
        newBook.setPrice(book.getPrice());
        newBook.setAuthor(book.getAuthor());
        newBook.setDate(book.getDate());
        newBook.setCategory(category);
        //Save to database
        bookRepository.save(newBook);

        return "Book Added";

    }


}
