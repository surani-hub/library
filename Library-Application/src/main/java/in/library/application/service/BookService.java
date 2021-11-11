package in.library.application.service;

import in.library.application.entity.Book;
import java.util.List;

public interface BookService {
   public Book saveBook(Book book);

   public List<Book> findAllBooks();

   public List<Book> findBookByName(String authorName);

   public List<Book> findBookByPrice(double price, String queryParam);

   public List<Book> findBookByTitleIgnoreCase(String title);

   public List<Book> findBookByPublishDate(Long publishDate, String queryParam);

}
