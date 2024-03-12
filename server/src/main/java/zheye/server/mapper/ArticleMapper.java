package zheye.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import zheye.server.entity.Article;

import java.util.List;


public interface ArticleMapper extends BaseMapper<Article> {
    List<Article> selectByUserId(int userId);
}
