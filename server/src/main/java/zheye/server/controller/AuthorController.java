package zheye.server.controller;

import zheye.server.entity.Author;

import zheye.server.service.AuthorService;

import org.springframework.web.bind.annotation.*;
import zheye.server.utils.Result;

import javax.annotation.Resource;

import java.util.List;

/**
 * (Author)表控制层
 *
 * @author makejava
 * @since 2023-02-22 23:38:44
 */
@RestController
@RequestMapping("/frontApi/Author")
public class AuthorController {
    /**
     * 服务对象
     */
    @Resource
    private AuthorService authorService;

    @PostMapping("/bookAuthor")
    public Result bookCoItem(@RequestBody int[] authorId) {
        List<Author> authors = authorService.getAuthors(authorId);
        return Result.ok(authors).message("获取收藏夹成功");
    }

    @PostMapping("/getAuthorById")
    public Result getAuthorById(@RequestBody int authorId) {
        Author author = authorService.getAuthorById(authorId);
        if (author != null) {
            return Result.ok(author).message("获取作者成功");
        } else {
            return Result.error().message("获取作者失败");
        }
    }

}

