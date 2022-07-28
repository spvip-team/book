package za.co.spvip.book.service;

import org.springframework.http.ResponseEntity;
import za.co.spvip.book.dto.BookDto;
import za.co.spvip.book.dto.BookPostDto;
import za.co.spvip.book.entity.Book;

import java.util.List;

public interface BookService {
    List<BookDto> finAll();
    ResponseEntity<String> save(BookPostDto bookDto);
    BookDto findById(Long id);
    ResponseEntity<String> delete(Long id);
    List<Book> search(String title);
    List<Book> getByCategoryId(Long categoryId);

    List<Book> getByAuthorName(String author);


}
