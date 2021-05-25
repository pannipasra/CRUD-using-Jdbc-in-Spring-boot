package com.example.demoCRUD_JDBC_3_1.DAO;

import com.example.demoCRUD_JDBC_3_1.POJO.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks(){
        String sql = "select * from book";
        List<Book> books =  jdbcTemplate.query(
                sql, BeanPropertyRowMapper.newInstance(Book.class));
        return books;
    }

    public Book getBookById(long id){
        String sql = "SELECT * from book WHERE id = ?";
        Object[] args = {id};
        Book book = jdbcTemplate.queryForObject(
                sql, args, BeanPropertyRowMapper.newInstance(Book.class)
        );
        return book;
    }

    public void saveBook(Book book){
        //using SimpleJdbcInsert
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("book").usingColumns("title", "auther", "price");
        //using BeanPropertySqlParameterSource
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(book);
        jdbcInsert.execute(parameterSource);
    }

    public void updateBook(Book book){
        String sql = "update book set title=:title, auther=:auther, price=:price where id=:id";

        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(book);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, parameterSource);
    }

    public void deleteBookById(long id){
        String sql = "delete from book where id =?";
        jdbcTemplate.update(sql, id);
    }

}
