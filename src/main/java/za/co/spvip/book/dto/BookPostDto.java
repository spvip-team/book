package za.co.spvip.book.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookPostDto {
    @NonNull
    private String title;
    private String description;
    private Double price;
    private String author;
    private Date date;
    @NotNull
    private long categoryId;
}
