package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import zheye.server.entity.BookComment;
import zheye.server.mapper.BookCommentMapper;
import zheye.server.service.BookCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (BookComment)表服务实现类
 *
 * @author makejava
 * @since 2023-02-24 14:30:16
 */
@Service("bookCommentService")
public class BookCommentServiceImpl extends ServiceImpl<BookCommentMapper, BookComment> implements BookCommentService {

    @Override
    public void commentSet(BookComment comment) {
        baseMapper.insert(comment);
    }

    @Override
    public List<BookComment> getCommentSet(int id) {
        QueryWrapper<BookComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id", id);
        return baseMapper.selectList(queryWrapper);
    }
}

