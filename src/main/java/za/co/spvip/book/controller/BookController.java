package za.co.spvip.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import za.co.spvip.book.dto.BookDto;
import za.co.spvip.book.dto.BookPostDto;
import za.co.spvip.book.entity.Book;
import za.co.spvip.book.entity.Category;
import za.co.spvip.book.repository.BookRepository;
import za.co.spvip.book.repository.CategoryRepository;
import za.co.spvip.book.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("books")
@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookService bookService;

    //Get all books
    @GetMapping
    public List<BookDto> getAll(){
        return bookService.finAll();
    }
    //Add book
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BookPostDto bookPostDto){
        return bookService.save(bookPostDto);
    }
    //Get book by id
    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id){
        //Book does not exist
        return bookService.findById(id);

    }


    //Get books by categoryId
    @GetMapping("/category/{id}")
    public List<Book> getByCategoryId(@PathVariable Long id){
        return bookService.getByCategoryId(id);
    }

    //Delete by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        return bookService.delete(id);
    }

    // Using the query parameter to get the title
    @GetMapping("/search")
    ResponseEntity<List<Book>> searchByTitle(@RequestParam(name = "query") String title){
        return ResponseEntity.ok(bookService.search(title));
    }

    //Get by author name
    @GetMapping("/author/{author}")
    public List<Book> getByAuthorName(@PathVariable String author){
        return bookService.getByAuthorName(author);
    }
    //Get by author name
    @GetMapping("/author")
    public List<Book> getByAuthor(@RequestParam("author") String author){
        return bookService.getByAuthorName(author);
    }





}
