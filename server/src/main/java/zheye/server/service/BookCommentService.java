package zheye.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zheye.server.entity.BookComment;

import javax.xml.stream.events.Comment;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * (BookComment)表服务接口
 *
 * @author makejava
 * @since 2023-02-24 14:30:16
 */
public interface BookCommentService extends IService<BookComment> {

    void commentSet(BookComment comment);

    Object getComment(int id) throws InvocationTargetException, IllegalAccessException;

    int updateComment(BookComment comment);


    BookComment getCommentById(int id);

    List<BookComment> getCommentByUserId(int userId);

    List<BookComment> selectCommentByCommentId(int commentId);

    List<BookComment> selectCommentByBookId(long bookId);

}

