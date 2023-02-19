
package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("books_id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));
            return book;
        });
        return new ArrayList<>(books);
    }

    public List<RecommendedBook> getRecommendedBooksData() {
        List<RecommendedBook> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) -> {
            RecommendedBook book = new RecommendedBook();
            book.setId(rs.getInt("books_id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));

            Float price = Float.valueOf(book.getPrice().substring(1));
            Float priceOld = Float.valueOf(book.getPriceOld().substring(1));
            book.setPriceDifference(priceOld - price);
            return book;
        });

        books.sort(Comparator.comparing(m -> -m.getPriceDifference()));

        return new ArrayList<>(books);
    }

    public List<String> getAuthorsTitle(char ch) {
        List<String> authorsTitle = jdbcTemplate.query("SELECT * FROM books WHERE author LIKE '" + ch + "%'", (ResultSet rs, int rowNum) -> {
            return rs.getString("author");
        });
        return new ArrayList<>(authorsTitle);
    }

}
