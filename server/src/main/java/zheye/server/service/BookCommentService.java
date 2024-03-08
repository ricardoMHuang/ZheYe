package zheye.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zheye.server.entity.BookComment;

import java.lang.reflect.InvocationTargetException;

/**
 * (BookComment)表服务接口
 *
 * @author makejava
 * @since 2023-02-24 14:30:16
 */
public interface BookCommentService extends IService<BookComment> {

    void commentSet(BookComment comment);

    Object getComment(int id) throws InvocationTargetException, IllegalAccessException;
}

