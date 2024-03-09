package zheye.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import zheye.server.entity.Article;
import zheye.server.entity.BookComment;
import zheye.server.entity.UserComment;

import java.util.List;


public interface BookCommentMapper extends BaseMapper<BookComment> {

    List<UserComment> selectUserAndCommentList(int id);

}
