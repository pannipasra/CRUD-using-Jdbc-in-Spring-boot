package com.example.demoCRUD_JDBC_3_1.Controller;

import com.example.demoCRUD_JDBC_3_1.DAO.BookDAO;
import com.example.demoCRUD_JDBC_3_1.POJO.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v3/book")
public class BookController {

    @Autowired
    private BookDAO bookDAO;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> books = bookDAO.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id){
        return new ResponseEntity<>(bookDAO.getBookById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        bookDAO.saveBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book){
        Book book1 = bookDAO.getBookById(id);
        book1.setTitle(book.getTitle());
        book1.setAuther(book.getAuther());
        book1.setPrice(book.getPrice());
        bookDAO.updateBook(book);
    return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Book> deleteBook(@PathVariable("id") long id){
        bookDAO.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
