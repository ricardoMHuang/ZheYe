package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import zheye.server.entity.Article;
import zheye.server.mapper.ArticleMapper;
import zheye.server.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2023-02-23 20:19:56
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public List<Article> getArticleItem() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Article getArticleInt(int id) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
//        System.out.println(baseMapper.selectOne(queryWrapper));
        return baseMapper.selectOne(queryWrapper);
    }
}

