package za.co.spvip.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.spvip.book.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

}

