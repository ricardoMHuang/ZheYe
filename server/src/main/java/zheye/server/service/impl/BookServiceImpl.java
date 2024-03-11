package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import zheye.server.entity.Book;
import zheye.server.mapper.BookMapper;
import zheye.server.service.BookService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * (Book)表服务实现类
 *
 * @author makejava
 * @since 2023-02-22 23:41:44
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Override
    public List<Book> getBooks() {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        System.out.print(baseMapper.selectList(queryWrapper));
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public String getBookName(int bookId) {
        return baseMapper.selectBookName(bookId);
    }

    @Override
    public Book getBookById(int bookId) {
        return baseMapper.getBookById(bookId);
    }

}
