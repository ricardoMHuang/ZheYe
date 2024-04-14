package zheye.server.controller;

import zheye.server.entity.Book;
import zheye.server.entity.BookType;
import zheye.server.service.BookService;
import zheye.server.service.BookTypeService;
import org.springframework.web.bind.annotation.*;
import zheye.server.utils.Result;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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
    @Resource
    private BookService bookService;

    @PostMapping("/type")
    public Result getType() {
        return Result.ok(bookTypeService.getBookType());
    }

    @PostMapping("/updateQuantity")
    public Result updateQuantity() {
        List<Integer> typeIdList = bookService.getBooks().stream()
                .map(Book::getTypeId) // 将每个Book对象映射为其类型ID
                .collect(Collectors.toList());
        typeIdList = typeIdList.stream()
                .sorted() // 根据自然顺序进行排序
                .collect(Collectors.toList());
        int left = 0, right = 0;
        for (; right < typeIdList.size(); right++) {
            if (!Objects.equals(typeIdList.get(right), typeIdList.get(left))) {
                updateQuantity(typeIdList.get(left), right - left);
                left = right;
            }
        }
        if (right == typeIdList.size()) {
            if (left == typeIdList.size() - 1)
                updateQuantity(typeIdList.get(left), 1);
            else
                updateQuantity(typeIdList.get(left), right - left);
        }
        return Result.ok();
    }

    public int updateQuantity(int id, int quantity) {
        return bookTypeService.updateQuantity(id, quantity);
    }
}

