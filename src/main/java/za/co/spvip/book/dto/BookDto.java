package za.co.spvip.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDto {
    private long id;
    private String title;
    private String description;
    private Double price;
    private String author;
    private Date date;
    private long categoryId;
    private String categoryName;
}
