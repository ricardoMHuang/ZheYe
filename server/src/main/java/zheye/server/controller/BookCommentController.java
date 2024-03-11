package zheye.server.controller;


import zheye.server.entity.BookComment;
import zheye.server.entity.Support;
import zheye.server.service.BookCommentService;
import org.springframework.web.bind.annotation.*;
import zheye.server.utils.Result;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * (BookComment)表控制层
 *
 * @author makejava
 * @since 2023-02-24 14:30:06
 */
@RestController
@RequestMapping("frontApi/bookDetail")
public class BookCommentController {
    @Resource
    private BookCommentService bookCommentService;

    @PostMapping("/submitComment")
    public Result bookCoItem(@RequestBody BookComment comment) {
        bookCommentService.commentSet(comment);
        return Result.ok().message("评论成功");
    }

    @PostMapping("/getComment")
    public Result getComment(@RequestBody int bookId) throws InvocationTargetException, IllegalAccessException {

        return Result.ok(bookCommentService.getComment(bookId)).message("获取评论成功");
    }

    @PostMapping("/updateComment")
    public Result updateComment(@RequestBody BookComment comment) {
//        System.out.println(comment.getSupportNum());
        if (bookCommentService.updateComment(comment) != 0) {
            return Result.ok().message("更新评论成功");
        } else {
            return Result.error().message("更新失败");
        }
    }

    @PostMapping("/getCommentById")
    public Result getCommentById(@RequestBody int id) {
        if (bookCommentService.getCommentById(id) != null) {
            return Result.ok(bookCommentService.getCommentById(id)).message("查找成功");
        } else {
            return Result.error().message("查找失败");
        }
    }

    @PostMapping("/getCommentByUserId")
    public Result getCommentByUserId(@RequestBody int userId) {
        List<BookComment> bookCommentList = bookCommentService.getCommentByUserId(userId);
        if (bookCommentList != null) {
            return Result.ok(bookCommentList).message("查找成功");
        } else {
            return Result.error().message("查找失败");
        }
    }

    @PostMapping("/selectCommentByCommentId")
    public Result selectCommentByCommentId(@RequestBody int commentId) {

        List<BookComment> bookCommentList = bookCommentService.selectCommentByCommentId(commentId);
//        System.out.println(commentId+""+bookCommentList);
        if (bookCommentList != null) {
            return Result.ok(bookCommentList).message("查找成功");
        } else {
            return Result.error().message("查找失败");
        }
    }

}

