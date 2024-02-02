package zheye.server.controller;


import zheye.server.entity.BookComment;
import zheye.server.service.BookCommentService;
import org.springframework.web.bind.annotation.*;
import zheye.server.utils.Result;

import javax.annotation.Resource;


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
    public Result getCommentItem(@RequestBody int id) {
        return Result.ok(bookCommentService.getCommentSet(id)).message("获取评论成功");
    }

}

