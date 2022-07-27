package za.co.spvip.book.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    private Double price;
    private String author;
    private Date date;
    //Relationship with Category
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    // Foreign key column name is category_id
    @JoinColumn(name = "categoryId",nullable = false)
    @JsonIgnore
    // Delete also de book when category is deleted
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;


}
