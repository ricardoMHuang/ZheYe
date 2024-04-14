package zheye.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zheye.server.entity.BookType;

import java.util.List;

/**
 * (BookType)表服务接口
 *
 * @author makejava
 * @since 2023-02-24 12:57:01
 */
public interface BookTypeService extends IService<BookType> {

    List<BookType> getBookType();

    int updateQuantity(int id, int totalQuantity);
}

