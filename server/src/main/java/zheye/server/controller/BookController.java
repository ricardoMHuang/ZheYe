package zheye.server.controller;


import zheye.server.entity.Book;

import zheye.server.service.BookService;

import org.springframework.web.bind.annotation.*;
import zheye.server.utils.Result;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Book)表控制层
 *
 * @author makejava
 * @since 2023-02-22 23:41:43
 */

@RestController
@RequestMapping("frontApi/book")
public class BookController {
    /**
     * 服务对象
     */
    @Resource
    private BookService bookService;

    @PostMapping("/bookItem")
    public Result bookItem() {
        List<Book> books = bookService.getBooks();
        return Result.ok(books).message("获取书籍成功");
    }

    /*  @PostMapping("/bookByType")
      public Result bookByType() {
          List<Book> books=bookService.getBooksByType();
          return Result.ok(books).message("获取书籍成功");
      }*/
    @PostMapping("/getBookById")
    public Result getBookById(@RequestBody int bookId) {
        Book book = bookService.getBookById(bookId);
        if (book != null) {

            return Result.ok(book).message("获取书籍成功");
        } else {
            return Result.error().message("获取书籍失败");
        }
    }

    @PostMapping("/getBookName")
    public Result getBookName(@RequestBody int bookId) {
        String bookName = bookService.getBookName(bookId);
        if (bookName != null) {

            return Result.ok(bookName).message("获取书籍名称成功");
        } else {
            return Result.error().message("获取书籍名称失败");
        }
    }

}

