package zheye.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zheye.server.entity.Article;

import java.util.List;

/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2023-02-23 20:19:56
 */
public interface ArticleService extends IService<Article> {

    public List<Article> getArticleItem();

    Article getArticleInt(int id);
}

