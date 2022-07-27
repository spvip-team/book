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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("books")
@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    //Get all books
    @GetMapping
    public List<BookDto> getAll(){
        List<BookDto> bookDtoList =new ArrayList<>();
        bookRepository.findAll().forEach(book -> {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setTitle(book.getTitle());
            bookDto.setDescription(book.getDescription());
            bookDto.setPrice(book.getPrice());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setDate(book.getDate());
            bookDto.setCategoryId(book.getCategory().getId());
            bookDto.setCategoryName(book.getCategory().getName());
            bookDtoList.add(bookDto);
        });
        return bookDtoList;
    }
    //Add book
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BookPostDto bookPostDto){
        Category category = categoryRepository.findById(bookPostDto.getCategoryId()).orElse(null);
        //Check if category exists
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category does not exist");
        }
        //Create a new book
        Book newBook = new Book();
        newBook.setTitle(bookPostDto.getTitle());
        newBook.setDescription(bookPostDto.getDescription());
        newBook.setPrice(bookPostDto.getPrice());
        newBook.setAuthor(bookPostDto.getAuthor());
        newBook.setDate(bookPostDto.getDate());
        newBook.setCategory(category);
        //Save to database
        bookRepository.save(newBook);

        return ResponseEntity.status(HttpStatus.CREATED).body("Book Added");

    }
    //Get book by id
    @GetMapping("{id}/")
    public BookDto getById(@PathVariable Long id){
        Optional<Book> book= bookRepository.findById(id);
        //Check if book exists
        if (book.isPresent()) {
            //Customize the bookDto
            BookDto bookDto = new BookDto();
            bookDto.setId(book.get().getId());
            bookDto.setTitle(book.get().getTitle());
            bookDto.setDescription(book.get().getDescription());
            bookDto.setPrice(book.get().getPrice());
            bookDto.setAuthor(book.get().getAuthor());
            bookDto.setDate(book.get().getDate());
            bookDto.setCategoryId(book.get().getCategory().getId());
            bookDto.setCategoryName(book.get().getCategory().getName());
            return bookDto;
        }
        //Book does not exist
        return null;

    }


    //Get books by categoryId
    @GetMapping("category/{id}")
    public List<Book> getByCategoryId(@PathVariable Long id){
        return bookRepository.findAllByCategoryId(id);
    }

    //Delete by Id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        Optional<Book> book= bookRepository.findById(id);
        if (book.isPresent()){
            bookRepository.delete(book.get());
            return ResponseEntity.ok("Book Deleted");
        }
        //Customize the response status Code
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book does not exist");
    }



    //Using the path variable to get the title
    @GetMapping("search/{title}")
    ResponseEntity<List<Book>> findByTitle(@PathVariable String title){
        return ResponseEntity.ok(bookRepository.findAllByTitleContaining(title));
    }

    // Using the query parameter to get the title
    @GetMapping("search")
    ResponseEntity<List<Book>> searchByTitle(@RequestParam(name = "query") String title){
        return ResponseEntity.ok(bookRepository.findAllByTitleContaining(title));
    }




}
