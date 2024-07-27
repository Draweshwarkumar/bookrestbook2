package com.api.book.bookrestbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.bookrestbook.dao.BookRepositor;
import com.api.book.bookrestbook.entities.Book;

@Component
public class bookservices {

    @Autowired
    private BookRepositor bookRepositor;

    // private static List<Book> list = new ArrayList<>();

    // static {
    // list.add(new Book(11212570, "java programming", "shivam jha"));
    // list.add(new Book(11212571, "SprignBoot programming", "Draweshwar kumar"));
    // list.add(new Book(11212572, "mern developer", "alish"));

    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepositor.findAll();
        return list;
    }

    public Book getBookById(int id) {
        Book book = null;
        // book = list.stream().filter(e -> e.getId() == id).findFirst().get();
        try {
            book = this.bookRepositor.findById(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return book;
    }

    public Book addBook(Book b) {
        Book result = bookRepositor.save(b);
        return result;
    }

    public void deleteBook(int bid) {
        bookRepositor.deleteById(bid);
        // list = list.stream().filter(book -> book.getId() !=
        // bid).collect(Collectors.toList());

    }

    public void updatebook(Book book, int bookId) {
        // list = list.stream().map(b -> {
        // if (b.getId() == bookId) {
        // b.setTitle(book.getTitle());
        // b.setAuthor(book.getAuthor());
        // }
        // return b;
        // }).collect(Collectors.toList());
        book.setId(bookId);
        bookRepositor.save(book);
    }
}
