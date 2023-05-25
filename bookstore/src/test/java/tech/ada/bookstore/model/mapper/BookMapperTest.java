package tech.ada.bookstore.model.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.model.entity.BookEntity;

import java.time.LocalDate;
import java.util.List;

public class BookMapperTest {

    private final BookMapper bookMapper = new BookMapper();

    @Test
    public void convertEntityToDTO() {

        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("TestTitle");
        bookEntity.setHistory("TestHistory");
        bookEntity.setSummary("TestSummary");
        bookEntity.setPrice(20.00);
        bookEntity.setPages(120);
        bookEntity.setIsbn("12039124124231");
        bookEntity.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        Assertions.assertEquals(bookDTO, bookMapper.convert(bookEntity));
    }

    @Test
    public void convertDTOToEntity() {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("TestTitle");
        bookEntity.setHistory("TestHistory");
        bookEntity.setSummary("TestSummary");
        bookEntity.setPrice(20.00);
        bookEntity.setPages(120);
        bookEntity.setIsbn("12039124124231");
        bookEntity.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        Assertions.assertEquals(bookEntity, bookMapper.convert(bookDTO));
    }

    @Test
    public void convertEntityListToDTO() {

        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("TestTitle");
        bookEntity.setHistory("TestHistory");
        bookEntity.setSummary("TestSummary");
        bookEntity.setPrice(20.00);
        bookEntity.setPages(120);
        bookEntity.setIsbn("12039124124231");
        bookEntity.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        Assertions.assertEquals(List.of(bookDTO), bookMapper.convertEntityListToDTO(List.of(bookEntity)));
    }

}
