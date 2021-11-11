package in.library.application.controller;

import in.library.application.entity.Book;
import in.library.application.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // 1) add books with title, author name, price, published date
    @PostMapping("/books")
    public Book saveBook(@RequestBody Book book) throws Exception{
        String publishDate = book.getPublishDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(publishDate);
            book.setPublishedDate(date.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return bookService.saveBook(book);
    }

    // 2) find book by name
    @GetMapping("/books/authorName")
    public List<Book> findBookByAuthorName(@RequestParam String authorName) {
        List<Book> bookList = Collections.emptyList();
        if (authorName != null && !authorName.isEmpty()) {
            bookList = bookService.findBookByName(authorName);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        bookList.stream().map(obj -> {
            calendar.setTimeInMillis(obj.getPublishedDate());
            obj.setPublishDate(sdf.format(calendar.getTime()));
            return  obj;
        }).collect(Collectors.toList());
        return bookList;
    }

    // 3) find books with price less than or greater than or equal to
    @GetMapping("/books/price")
    public List<Book> findBookByPrice(@RequestParam Double price,
                                      @RequestParam String queryParam) {
        List<Book> bookList = Collections.emptyList();
        if (price != null && queryParam != null && !queryParam.isEmpty()) {
            bookList = bookService.findBookByPrice(price, queryParam);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        bookList.stream().map(obj -> {
            calendar.setTimeInMillis(obj.getPublishedDate());
            obj.setPublishDate(sdf.format(calendar.getTime()));
            return  obj;
        }).collect(Collectors.toList());
        return bookList;
    }

    // 4) find books by published date less than or greater than or equal to
    @GetMapping("/books/publishdate")
    public List<Book> findBookByPublishDate(@RequestParam String publishDate,
                               @RequestParam String queryParam) {
        List<Book> bookList = Collections.emptyList();
        if (publishDate != null && queryParam != null && !queryParam.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(publishDate);
                bookList = bookService.findBookByPublishDate(date.getTime(), queryParam);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        bookList.stream().map(obj -> {
            calendar.setTimeInMillis(obj.getPublishedDate());
            obj.setPublishDate(sdf.format(calendar.getTime()));
            return  obj;
        }).collect(Collectors.toList());

        return bookList;
    }

    // 2) find book by title
    @GetMapping("/books/title")
    public List<Book> findBookByTitle(@RequestParam String title) {
        List<Book> bookList = Collections.emptyList();
        if (title != null && !title.isEmpty()) {
            bookList = bookService.findBookByTitleIgnoreCase(title);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        bookList.stream().map(obj -> {
            calendar.setTimeInMillis(obj.getPublishedDate());
            obj.setPublishDate(sdf.format(calendar.getTime()));
            return  obj;
        }).collect(Collectors.toList());
        return bookList;
    }

    // 5) get all books sorted by price, sorted by author name, sorted by date
    @GetMapping("/books/sortedby")
    public List<Book> findBookSortByPriceAuthorNameDate() {
        List<Book> bookList = bookService.findAllBooks().stream().sorted(Comparator.comparing(Book::getPrice)
                    .thenComparing(Book::getAuthorName)
                    .thenComparing(Book::getPublishedDate))
                    .collect(Collectors.toList());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        bookList.stream().map(obj -> {
        calendar.setTimeInMillis(obj.getPublishedDate());
        obj.setPublishDate(sdf.format(calendar.getTime()));
        return  obj;
        }).collect(Collectors.toList());

        return bookList;
    }

}
