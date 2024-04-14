package zheye.server.service;

import zheye.server.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Book)表服务接口
 *
 * @author makejava
 * @since 2023-02-22 23:41:44
 */

public interface BookService {
    List<Book> getBooks();

    String getBookName(int bookId);

    Book getBookById(Long bookId);

    List<Book> getHotList();

    List<Book> getBookByType(int id);


    /*  List<Book> getBooksByType();*/
}
