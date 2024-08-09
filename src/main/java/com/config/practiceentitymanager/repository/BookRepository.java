package com.config.practiceentitymanager.repository;

import com.config.practiceentitymanager.exception.NotFoundException;
import com.config.practiceentitymanager.model.Book;
import com.config.practiceentitymanager.model.dto.BookRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
@Transactional
@AllArgsConstructor
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final ModelMapper modelMapper;

    public Book createBook(BookRequest bookRequest) {
        Book book = modelMapper.map(bookRequest, Book.class);
        entityManager.persist(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return entityManager.createQuery("SELECT b FROM books b",Book.class).getResultList();
    }

    public Book getBookById(UUID bookId) {
        Book book = entityManager.find(Book.class, bookId);
        if (book == null){
            throw new NotFoundException("Book not found");
        }
        return book;
    }

    public Book updateBookById(UUID bookId, BookRequest bookRequest) {
        Book book = getBookById(bookId);
        entityManager.detach(book);
        modelMapper.map(bookRequest, book);
        entityManager.merge(book);
        return book;
    }

    public void deleteBookById(UUID bookId) {
        Book book = getBookById(bookId);
        entityManager.remove(book);
    }

    public List<Book> getBookByTitle(String title) {
        List<Book> book = entityManager.createQuery(
                        "SELECT b FROM books b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :custTitle, '%'))",
                        Book.class
                )
                .setParameter("custTitle", title)
                .getResultList();

        if (book.isEmpty()){
            throw new NotFoundException("Book title ("+title+") not found");
        }
        return book;
    }
}
