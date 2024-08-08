package com.config.practiceentitymanager.repository;

import com.config.practiceentitymanager.exception.NotFoundException;
import com.config.practiceentitymanager.model.Book;
import com.config.practiceentitymanager.model.dto.BookRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
@Transactional
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Book createBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setDescription(bookRequest.getDescription());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublicationYear(bookRequest.getPublicationYear());
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
        book.setTitle(bookRequest.getTitle());
        book.setDescription(bookRequest.getDescription());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublicationYear(bookRequest.getPublicationYear());
        entityManager.merge(book);
        return book;
    }

    public void deleteBookById(UUID bookId) {
        Book book = getBookById(bookId);
        entityManager.remove(book);
    }
}
