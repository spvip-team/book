package za.co.spvip.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.co.spvip.book.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    //
    List<Book> findAllByCategoryId(Long categoryId);

    //SELECT * FROM book WHERE title LIKE '%title%'
    List<Book> findAllByTitleContaining(String title);



}
