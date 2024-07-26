package com.api.book.bookrestbook.services;

import com.api.book.bookrestbook.entities.Book;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class bookservices {

    private static List<Book> list = new ArrayList<>();

    static {
        list.add(new Book(11212570, "java programming", "shivam jha"));
        list.add(new Book(11212571, "SprignBoot programming", "Draweshwar kumar"));
        list.add(new Book(11212572, "mern developer", "alish"));
    }

    public List<Book> getAllBooks() {
        return list;
    }

    public Book getBookById(int id) {
        Book book = null;
        book = list.stream().filter(e -> e.getId() == id).findFirst().get();
        return book;
    }

    public Book addBook(Book b) {
        list.add(b);
        return b;
    }

    public void deleteBook(int bid) {
        list = list.stream().filter(book -> book.getId() != bid).collect(Collectors.toList());
    }

    public void updatebook(Book book, int bookId) {
        list = list.stream().map(b -> {
            if (b.getId() == bookId) {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
            }
            return b;
        }).collect(Collectors.toList());
    }

}
