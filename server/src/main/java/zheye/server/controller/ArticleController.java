package zheye.server.controller;


import zheye.server.entity.Article;
import zheye.server.service.ArticleService;
import org.springframework.web.bind.annotation.*;
import zheye.server.utils.Result;

import javax.annotation.Resource;

/**
 * (Article)表控制层
 *
 * @author makejava
 * @since 2023-02-23 20:19:56
 */
@RestController
@RequestMapping("frontApi/article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    @PostMapping("/articleItem")
    public Result getArticles() {
        return Result.ok(articleService.getArticleItem());
    }

    @PostMapping("/articlePlease")
    public Result getArticleItem(@RequestBody int id) {
        Article article = articleService.getArticleInt(id);
        if (article != null) {
            return Result.ok(article).message("获取推文成功");
        } else {
            return Result.error().message("获取推文失败");
        }
    }
}

