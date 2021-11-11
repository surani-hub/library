package in.library.application.service;

import in.library.application.entity.Book;
import in.library.application.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBookByName(String authorName) {
        return bookRepository.findByAuthorNameIgnoreCase(authorName);
    }

    @Override
    public List<Book> findBookByPrice(double price, String queryParam) {
        if(queryParam.equals("=")){
            return bookRepository.findByPriceEquals(price);
        }else if(queryParam.equals("<")){
            return bookRepository.findByPriceLessThan(price);
        }else if(queryParam.equals(">")){
            return bookRepository.findByPriceGreaterThan(price);
        }else {
            String.format("Invalid query Param");
        }
       return Collections.emptyList();
    }

    @Override
    public List<Book> findBookByPublishDate(Long dateInMillSecs, String queryParam) {
        if(queryParam.equals("=")){
            return bookRepository.findBookByPublishedDateEquals(dateInMillSecs);
        }else if(queryParam.equals("<")){
            return bookRepository.findBookByPublishedDateLessThan(dateInMillSecs);
        }else if(queryParam.equals(">")){
            return bookRepository.findBookByPublishedDateGreaterThan(dateInMillSecs);
        }else {
            String.format("Invalid query Param");
        }
        return Collections.emptyList();
    }

    @Override
    public List<Book> findBookByTitleIgnoreCase(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }

}
