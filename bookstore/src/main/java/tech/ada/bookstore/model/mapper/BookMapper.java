package tech.ada.bookstore.model.mapper;

import org.springframework.stereotype.Component;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.model.entity.BookEntity;

import java.util.List;

@Component
public class BookMapper {

    public BookDTO convert(BookEntity book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setHistory(book.getHistory());
        bookDTO.setSummary(book.getSummary());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setPages(book.getPages());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublicationDate(book.getPublicationDate());
        return bookDTO;
    }

    public BookEntity convert(BookDTO book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setHistory(book.getHistory());
        bookEntity.setSummary(book.getSummary());
        bookEntity.setPrice(book.getPrice());
        bookEntity.setPages(book.getPages());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setPublicationDate(book.getPublicationDate());
        return bookEntity;
    }

    public List<BookDTO> convertEntityListToDTO(List<BookEntity> listBooks) {
        return listBooks.stream()
                .map(this::convert)
                .toList();
    }

    public List<BookEntity> convertDTOListToEntity(List<BookDTO> listBooks) {
        return listBooks.stream()
                .map(this::convert)
                .toList();
    }

}
