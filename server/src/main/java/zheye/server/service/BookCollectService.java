package zheye.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zheye.server.entity.Book;
import zheye.server.entity.BookCollect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import zheye.server.entity.User;
import zheye.server.utils.Result;

import java.util.List;

/**
 * (BookCollect)表服务接口
 *
 * @author makejava
 * @since 2023-02-22 00:06:01
 */
@Service
@Transactional

public interface BookCollectService extends IService<BookCollect> {
    List<BookCollect> bookCoItem(BookCollect bookCollect);

    void addBook(BookCollect bookCollect);

    boolean addBookCollection(BookCollect bookCollect);

    boolean deleteBookCollection(BookCollect bookCollect);

    BookCollect selectBookCollect(BookCollect bookCollect);
}
