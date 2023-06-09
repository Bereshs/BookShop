package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Service
public class BookService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData() {
        List<Book> listBooks = jdbcTemplate.query("SELECT BOOKS.id id, BOOKS.TITLE tittle, BOOKS.PRICEOLD priceOld, BOOKS.PRICE price, author.name author FROM BOOKS INNER JOIN AUTHOR ON BOOKS.AUTHOR=AUTHOR.NAME", (ResultSet rs, int numRows) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));
            book.setTitle(rs.getString("title"));
            return book;
        });
        Logger.getLogger(BookService.class.getName()).info(" " + listBooks.size());
        return new ArrayList<>(listBooks);
    }
}
