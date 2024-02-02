package zheye.server.controller;

import zheye.server.entity.Book;
import zheye.server.entity.BookCollect;

import org.springframework.web.bind.annotation.*;
import zheye.server.service.BookCollectService;
import zheye.server.utils.Result;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BookCollect)表控制层
 *
 * @author makejava
 * @since 2023-02-22 00:06:01
 */
@RestController
@RequestMapping("/frontApi/bookCollect")
public class BookCollectController {

    @Resource
    private BookCollectService bookCollectService;

    @PostMapping("/bookCoItem")
    public Result bookCoItem(@RequestBody BookCollect bookCollect) {
        List<BookCollect> bookCollects = bookCollectService.bookCoItem(bookCollect);
        return Result.ok(bookCollects).message("获取收藏夹成功");
    }

    @PostMapping("/addCollect")
    public Result addCollect(@RequestBody BookCollect bookCollect) {
        bookCollectService.addBook(bookCollect);
        return Result.ok().message("获取收藏夹成功");
    }

    @PostMapping("/deleteBookCollection")
    public Result deleteBookCollection(@RequestBody BookCollect bookCollect) {
        System.out.println(bookCollect);
        if (bookCollectService.deleteBookCollection(bookCollect)) {
            System.out.println(Result.ok().message("成功删除"));
            return Result.ok().message("成功删除");
        } else {
            System.out.println(Result.error().message("删除失败"));
            return Result.error().message("删除失败");
        }
    }

    @PostMapping("/addBookCollection")
    public Result addBookCollection(@RequestBody BookCollect bookCollect) {
        System.out.println(bookCollect);
        if (bookCollectService.deleteBookCollection(bookCollect)) {
            System.out.println(Result.ok().message("成功删除"));
            return Result.ok().message("成功删除");
        } else {
            System.out.println(Result.error().message("删除失败"));
            return Result.error().message("删除失败");
        }
    }
}

