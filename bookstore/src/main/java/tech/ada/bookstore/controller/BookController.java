package tech.ada.bookstore.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.model.dto.MessageDTO;
import tech.ada.bookstore.service.BookService;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody BookDTO bookDTO) {

        try {

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bookService.create(bookDTO));
        } catch (Exception exception) {

            log.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(exception.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Object> read() {

        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bookService.read());

        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(exception.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody BookDTO bookDTO, @PathVariable("id") Integer id) {

        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bookService.update(bookDTO, id));

        } catch (EntityNotFoundException exception) {

            log.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO(exception.getMessage()));
        } catch (Exception exception) {

            log.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(exception.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {

        try {
            bookService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MessageDTO("Book " + id + " was deleted with success."));

        } catch (EntityNotFoundException exception) {
            log.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO(exception.getMessage()));

        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(exception.getMessage()));
        }
    }

}
