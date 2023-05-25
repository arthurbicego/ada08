package tech.ada.bookstore.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.model.entity.BookEntity;
import tech.ada.bookstore.model.mapper.BookMapper;
import tech.ada.bookstore.repository.BookRepository;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

public class BookServiceTest {

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void init() {
        //noinspection deprecation
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        BookDTO bookDTOId = new BookDTO();
        bookDTOId.setId(1L);
        bookDTOId.setTitle("TestTitle");
        bookDTOId.setHistory("TestHistory");
        bookDTOId.setSummary("TestSummary");
        bookDTOId.setPrice(20.00);
        bookDTOId.setPages(120);
        bookDTOId.setIsbn("12039124124231");
        bookDTOId.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("TestTitle");
        bookEntity.setHistory("TestHistory");
        bookEntity.setSummary("TestSummary");
        bookEntity.setPrice(20.00);
        bookEntity.setPages(120);
        bookEntity.setIsbn("12039124124231");
        bookEntity.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        BookEntity bookEntityId = new BookEntity();
        bookEntityId.setId(1L);
        bookEntityId.setTitle("TestTitle");
        bookEntityId.setHistory("TestHistory");
        bookEntityId.setSummary("TestSummary");
        bookEntityId.setPrice(20.00);
        bookEntityId.setPages(120);
        bookEntityId.setIsbn("12039124124231");
        bookEntityId.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));


        when(bookMapper.convert(bookDTO)).thenReturn(bookEntity);

        when(bookRepository.save(bookEntity)).thenReturn(bookEntityId);

        when(bookMapper.convert(bookEntityId)).thenReturn(bookDTOId);

        Assertions.assertEquals(bookDTOId, bookService.create(bookDTO));

    }

}
