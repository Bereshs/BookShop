package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static java.util.Objects.isNull;

@Service
public class AuthorService {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public HashMap<String, List<Author>> getAuthorsData() {
        HashMap<String, List<Author>> listAuthors = jdbcTemplate.query("SELECT * FROM AUTHOR", (ResultSet rs) -> {
            HashMap<String, List<Author>> authorMap = new HashMap<>();
            while (rs.next()) {
                String name = rs.getString("name");
                String key = name.substring(0, 1).toUpperCase();
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(name);
                List<Author> authorsLetterList = isNull(authorMap.get(key)) ? new ArrayList<>() : authorMap.get(key);
                authorsLetterList.add(author);
                authorMap.put(key, authorsLetterList);
            }
            return authorMap;
        });
        return listAuthors;
    }

}
