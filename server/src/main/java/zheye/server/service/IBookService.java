package zheye.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zheye.server.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import zheye.server.utils.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李嘉图
 * @since 2023-02-10
 */
@Service
@Transactional
public interface IBookService extends IService<Book> {


    List<Book> findByReaderNumber();
}
