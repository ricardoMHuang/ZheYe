package zheye.server.controller;


import zheye.server.service.DirectoryService;
import org.springframework.web.bind.annotation.*;
import zheye.server.utils.Result;

import javax.annotation.Resource;


/**
 * 目录(Directory)表控制层
 *
 * @author makejava
 * @since 2023-02-23 22:43:22
 */
@RestController
@RequestMapping("frontApi/directory")
public class DirectoryController {
    /**
     * 服务对象
     */
    @Resource
    private DirectoryService directoryService;

    @PostMapping("/directories")
    public Result getDirectory(@RequestBody int id) {

        return Result.ok(directoryService.getDirectoryItem(id));
    }

    @PostMapping("/bookContent")
    public Result getBookContent(@RequestBody int bookId) {
        return Result.ok(directoryService.getDirectoryItem(bookId));

    }

    @PostMapping("/getBookChapter")
    public Result getBookChapter(@RequestBody int bookId) {
        return Result.ok(directoryService.getBookChapter(bookId));

    }
}

