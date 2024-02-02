package .controller;

import .entity.BookCollect;
import .service.BookCollectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BookCollect)表控制层
 *
 * @author makejava
 * @since 2023-02-22 00:03:04
 */
@RestController
@RequestMapping("bookCollect")
public class BookCollectController {
    /**
     * 服务对象
     */
    @Resource
    private BookCollectService bookCollectService;

    /**
     * 分页查询
     *
     * @param bookCollect 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<BookCollect>> queryByPage(BookCollect bookCollect, PageRequest pageRequest) {
        return ResponseEntity.ok(this.bookCollectService.queryByPage(bookCollect, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<BookCollect> queryById(@PathVariable("id")  id) {
        return ResponseEntity.ok(this.bookCollectService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param bookCollect 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<BookCollect> add(BookCollect bookCollect) {
        return ResponseEntity.ok(this.bookCollectService.insert(bookCollect));
    }

    /**
     * 编辑数据
     *
     * @param bookCollect 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<BookCollect> edit(BookCollect bookCollect) {
        return ResponseEntity.ok(this.bookCollectService.update(bookCollect));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById( id) {
        return ResponseEntity.ok(this.bookCollectService.deleteById(id));
    }

}

