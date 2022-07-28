package za.co.spvip.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.spvip.book.dto.BookDto;
import za.co.spvip.book.dto.BookPostDto;
import za.co.spvip.book.entity.Book;
import za.co.spvip.book.entity.Category;
import za.co.spvip.book.repository.BookRepository;
import za.co.spvip.book.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public List<BookDto> finAll() {
        List<BookDto> bookDtoList =new ArrayList<>();
        bookRepository.findAll().forEach(book -> {
            if (book.getCategory() != null) {
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
            }
        });
        return bookDtoList;
    }

    @Override
    public ResponseEntity<String> save(BookPostDto bookPostDto) {
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

    @Override
    public BookDto findById(Long id) {
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
        return null;
    }
    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Book> book= bookRepository.findById(id);
        if (book.isPresent()){
            bookRepository.delete(book.get());
            return ResponseEntity.ok("Book Deleted");
        }
        //Customize the response status Code
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book does not exist");
    }

    @Override
    public List<Book> search(String title) {
        return bookRepository.findAllByTitleContaining(title)  ;
    }

    @Override
    public List<Book> getByCategoryId(Long categoryId) {
      return   bookRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Book> getByAuthorName(String author) {
        return bookRepository.findAllByAuthor(author);
    }
}
