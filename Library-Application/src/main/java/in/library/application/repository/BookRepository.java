package in.library.application.repository;

import in.library.application.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findByAuthorNameIgnoreCase(String authorName);

    public List<Book> findByPriceLessThan(double price);

    public List<Book> findByPriceGreaterThan(double price);

    public List<Book> findByPriceEquals(double price);

    public List<Book> findByTitleIgnoreCase(String title);

    public List<Book> findBookByPublishedDateGreaterThan(Long publishDate);

    public List<Book> findBookByPublishedDateLessThan(Long publishDate);

    public List<Book> findBookByPublishedDateEquals(Long publishDate);

}
