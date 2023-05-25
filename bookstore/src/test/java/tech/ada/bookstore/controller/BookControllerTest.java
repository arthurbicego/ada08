package tech.ada.bookstore.controller;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.model.dto.MessageDTO;
import tech.ada.bookstore.service.BookService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void init() {
        //noinspection deprecation
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createSuccess() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        when(bookService.create(bookDTO)).thenReturn(bookDTO);

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).body(bookDTO), bookController.create(bookDTO));
    }

    @Test
    public void createFail() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        when(bookService.create(bookDTO)).thenThrow(new NullPointerException("Bad Request."));

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO("Bad Request.")), bookController.create(bookDTO));
    }

    @Test
    public void readSuccess() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        when(bookService.read()).thenReturn(List.of(bookDTO));

        Assertions.assertEquals(ResponseEntity.ok(List.of(bookDTO)), bookController.read());
    }

    @Test
    public void readFail() {
        when(bookService.read()).thenThrow(new NullPointerException("Bad Request."));

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO("Bad Request.")), bookController.read());
    }

    @Test
    public void updateSuccess() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        when(bookService.update(bookDTO, 1)).thenReturn(bookDTO);

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).body(bookDTO), bookController.update(bookDTO, 1));
    }

    @Test
    public void updateFailNotFound() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        when(bookService.update(bookDTO, 1)).thenThrow(new EntityNotFoundException("Book not found."));

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO("Book not found.")), bookController.update(bookDTO, 1));
    }

    @Test
    public void updateFailBadRequest() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        when(bookService.update(bookDTO, 1)).thenThrow(new NullPointerException("Bad Request."));

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO("Bad Request.")), bookController.update(bookDTO, 1));
    }

    @Test
    public void deleteSuccess() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        doNothing().when(bookService).delete(1);

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).body(new MessageDTO("Book 1 was deleted with success.")), bookController.delete(1));
    }

    @Test
    public void deleteFailNotFound() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        doThrow(new EntityNotFoundException("Book not found.")).when(bookService).delete(1);

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO("Book not found.")), bookController.delete(1));
    }

    @Test
    public void deleteFailBadRequest() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)));

        doThrow(new NullPointerException("Bad Request.")).when(bookService).delete(1);

        Assertions.assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO("Bad Request.")), bookController.delete(1));
    }

}
