package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import zheye.server.entity.BookType;
import zheye.server.mapper.BookTypeMapper;
import zheye.server.service.BookTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (BookType)表服务实现类
 *
 * @author makejava
 * @since 2023-02-24 12:57:01
 */
@Service
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {

    @Override
    public List<BookType> getBookTpye() {
        QueryWrapper<BookType> queryWrapper = new QueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }
}

