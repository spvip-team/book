package za.co.spvip.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.spvip.book.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    //


}
