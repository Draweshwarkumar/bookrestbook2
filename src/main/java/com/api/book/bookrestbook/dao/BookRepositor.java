package com.api.book.bookrestbook.dao;

import org.springframework.data.repository.CrudRepository;
import com.api.book.bookrestbook.entities.Book;

public interface BookRepositor extends CrudRepository<Book, Integer> {
    public Book findById(int id);
}
