package zheye.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import zheye.server.entity.Book;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李嘉图
 * @since 2023-02-10
 */
public interface BookMapper extends BaseMapper<Book> {

    String selectBookName(int bookId);

    Book getBookById(Long bookId);
}
