package tech.ada.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.model.entity.BookEntity;
import tech.ada.bookstore.repository.BookRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookControllerIT {

    @Autowired
    private BookController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository repository;

    @BeforeEach
    public void cleanRepository () {
        repository.deleteAll();
    }

    @Test
    public void loadContext(){
        Assertions.assertNotNull(controller);
    }

    @Test
    public void createSuccess() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Optional<BookEntity> book = repository.findById(1);

        Assertions.assertTrue(book.isPresent());
        Assertions.assertEquals("TestTitle", book.get().getTitle());
        Assertions.assertEquals("TestHistory", book.get().getHistory());
        Assertions.assertEquals("TestSummary", book.get().getSummary());
        Assertions.assertEquals(20.00, book.get().getPrice());
        Assertions.assertEquals(120, book.get().getPages());
        Assertions.assertEquals("12039124124231", book.get().getIsbn());
        Assertions.assertEquals(Date.valueOf(LocalDate.now().plusDays(1)), book.get().getPublicationDate());
    }

    @Test
    public void createTitleMandatoryFail() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(" ");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createHistoryMandatoryFail() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTile");
        bookDTO.setHistory(" ");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createHistoryBiggerThan500Fail() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTile");
        bookDTO.setHistory("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sit amet rhoncus nulla. Donec vehicula, nulla vitae venenatis egestas, urna dui sollicitudin diam, volutpat dapibus metus risus vitae ligula. Nullam non lorem accumsan, interdum purus a, sagittis nibh. Suspendisse a pharetra mi, ac fermentum dui. Phasellus dignissim ante quis maximus semper. Praesent posuere, libero sit amet convallis pellentesque, massa dui tristique leo, luctus volutpat velit mauris et ex. Donec venenatis blandit.");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createPriceMandatoryFail() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTile");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(null);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createPriceLessThan20Fail() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTile");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(19.99);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createPagesMandatoryFail() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTile");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(null);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createPagesLessThan100Fail() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTile");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(99);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createIsbnMandatoryFail() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTile");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn(" ");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createFutureDateFail() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTile");
        bookDTO.setHistory("TestHistory");
        bookDTO.setSummary("TestSummary");
        bookDTO.setPrice(20.00);
        bookDTO.setPages(120);
        bookDTO.setIsbn("12039124124231");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().minusDays(1)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void readSuccess () throws Exception {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("TestTitle");
        bookEntity.setHistory("TestHistory");
        bookEntity.setSummary("TestSummary");
        bookEntity.setPrice(20.00);
        bookEntity.setPages(120);
        bookEntity.setIsbn("12039124124231");
        bookEntity.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        repository.save(bookEntity);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookEntity);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateSuccess () throws Exception {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("TestTitle");
        bookEntity.setHistory("TestHistory");
        bookEntity.setSummary("TestSummary");
        bookEntity.setPrice(20.00);
        bookEntity.setPages(120);
        bookEntity.setIsbn("12039124124231");
        bookEntity.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        repository.save(bookEntity);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("TestTitle New");
        bookDTO.setHistory("TestHistory New");
        bookDTO.setSummary("TestSummary New");
        bookDTO.setPrice(21.00);
        bookDTO.setPages(130);
        bookDTO.setIsbn("12039124124231 New");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(2)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/books/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Optional<BookEntity> book = repository.findById(1);

        Assertions.assertTrue(book.isPresent());
        Assertions.assertEquals("TestTitle New", book.get().getTitle());
        Assertions.assertEquals("TestHistory New", book.get().getHistory());
        Assertions.assertEquals("TestSummary New", book.get().getSummary());
        Assertions.assertEquals(21.00, book.get().getPrice());
        Assertions.assertEquals(130, book.get().getPages());
        Assertions.assertEquals("12039124124231 New", book.get().getIsbn());
        Assertions.assertEquals(Date.valueOf(LocalDate.now().plusDays(2)), book.get().getPublicationDate());
    }

    @Test
    public void updateBookNotFound () throws Exception {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("TestTitle");
        bookEntity.setHistory("TestHistory");
        bookEntity.setSummary("TestSummary");
        bookEntity.setPrice(20.00);
        bookEntity.setPages(120);
        bookEntity.setIsbn("12039124124231");
        bookEntity.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        repository.save(bookEntity);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("TestTitle New");
        bookDTO.setHistory("TestHistory New");
        bookDTO.setSummary("TestSummary New");
        bookDTO.setPrice(21.00);
        bookDTO.setPages(130);
        bookDTO.setIsbn("12039124124231 New");
        bookDTO.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(2)));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(bookDTO);

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/books/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(content().string("{\"mesage\":\"Book not found.\"}"));

        Optional<BookEntity> book = repository.findById(1);

        Assertions.assertTrue(book.isPresent());
        Assertions.assertEquals("TestTitle", book.get().getTitle());
        Assertions.assertEquals("TestHistory", book.get().getHistory());
        Assertions.assertEquals("TestSummary", book.get().getSummary());
        Assertions.assertEquals(20.00, book.get().getPrice());
        Assertions.assertEquals(120, book.get().getPages());
        Assertions.assertEquals("12039124124231", book.get().getIsbn());
        Assertions.assertEquals(Date.valueOf(LocalDate.now().plusDays(1)), book.get().getPublicationDate());
    }

    @Test
    public void deleteSuccess () throws Exception {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("TestTitle");
        bookEntity.setHistory("TestHistory");
        bookEntity.setSummary("TestSummary");
        bookEntity.setPrice(20.00);
        bookEntity.setPages(120);
        bookEntity.setIsbn("12039124124231");
        bookEntity.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        repository.save(bookEntity);

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/books/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("{\"mesage\":\"Book 1 was deleted with success.\"}"));

        Optional<BookEntity> book = repository.findById(1);

        Assertions.assertFalse(book.isPresent());

    }

    @Test
    public void deleteBookNotFound () throws Exception {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("TestTitle");
        bookEntity.setHistory("TestHistory");
        bookEntity.setSummary("TestSummary");
        bookEntity.setPrice(20.00);
        bookEntity.setPages(120);
        bookEntity.setIsbn("12039124124231");
        bookEntity.setPublicationDate(Date.valueOf(LocalDate.now().plusDays(1)));

        repository.save(bookEntity);

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/books/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(content().string("{\"mesage\":\"Book not found.\"}"));

        Optional<BookEntity> book = repository.findById(1);

        Assertions.assertTrue(book.isPresent());

    }




}
