package zheye.server.controller;

import zheye.server.entity.BookType;
import zheye.server.service.BookTypeService;
import org.springframework.web.bind.annotation.*;
import zheye.server.utils.Result;

import javax.annotation.Resource;
import java.util.List;


/**
 * (BookType)表控制层
 *
 * @author makejava
 * @since 2023-02-24 12:56:45
 */
@RestController
@RequestMapping("frontApi/bookType")
public class BookTypeController {
    /**
     * 服务对象
     */
    @Resource
    private BookTypeService bookTypeService;

    @PostMapping("/type")
    public Result getType() {
        return Result.ok(bookTypeService.getBookTpye());
    }
}

