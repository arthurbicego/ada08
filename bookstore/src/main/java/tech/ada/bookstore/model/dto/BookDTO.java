package tech.ada.bookstore.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class BookDTO {

    private Long id;

    @NotBlank(message = "Title must contain some value.")
    private String title;

    @NotBlank
    @Size(max = 500, message = "History must not be bigger than 500 characters.")
    private String history;

    private String summary;

    @NotNull
    @Min(value = 20, message = "Price must not be less than 20.")
    private Double price;

    @NotNull
    @Min(value = 100, message = "Pages must not be less than 100.")
    private Integer pages;

    @NotBlank
    private String isbn;

    @Future(message = "Date must be in the future.")
    private Date publicationDate;
}
