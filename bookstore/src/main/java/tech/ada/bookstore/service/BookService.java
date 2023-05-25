package tech.ada.bookstore.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.model.entity.BookEntity;
import tech.ada.bookstore.model.mapper.BookMapper;
import tech.ada.bookstore.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper mapper;

    public BookDTO create(BookDTO bookDTO) {

        BookEntity book = mapper.convert(bookDTO);

        System.out.println(book);

        book = repository.save(book);

        return mapper.convert(book);
    }

    public List<BookDTO> read() {
        List<BookEntity> listEntities = repository.findAll();
        return mapper.convertEntityListToDTO(listEntities);
    }

    public BookDTO update(BookDTO bookDTO, Integer id) {

        if (repository.existsById(id)) {

            BookEntity bookEntity = mapper.convert(bookDTO);
            bookEntity.setId(Long.valueOf(id));
            bookEntity = repository.save(bookEntity);

            return mapper.convert(bookEntity);
        }

        throw new EntityNotFoundException("Book not found.");
    }

    public void delete(Integer id) {
        Optional<BookEntity> bookOp = repository.findById(id);

        if (bookOp.isPresent()) {
            BookEntity bookEntity = bookOp.get();
            repository.delete(bookEntity);
            return;
        }

        throw new EntityNotFoundException("Book not found.");
    }

}
