package za.co.spvip.book.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;


}
