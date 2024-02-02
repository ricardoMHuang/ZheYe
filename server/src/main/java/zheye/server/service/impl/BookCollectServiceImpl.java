package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import zheye.server.entity.BookCollect;
import zheye.server.mapper.BookCollectMapper;
import zheye.server.service.BookCollectService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (BookCollect)表服务实现类
 *
 * @author makejava
 * @since 2023-02-22 00:06:01
 */
@Service
public class BookCollectServiceImpl extends ServiceImpl<BookCollectMapper, BookCollect> implements BookCollectService {
    @Override
    public List<BookCollect> bookCoItem(BookCollect bookCollect) {
        QueryWrapper<BookCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!ObjectUtils.isEmpty(bookCollect.getUserId()), "user_id", bookCollect.getUserId());
        System.out.print(baseMapper.selectList(queryWrapper));
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void addBook(BookCollect bookCollect) {
        baseMapper.insert(bookCollect);
    }

    @Override
    public boolean addBookCollection(BookCollect bookCollect) {
        return baseMapper.addBookCollection(bookCollect);
    }

    @Override
    public boolean deleteBookCollection(BookCollect bookCollect) {
        return baseMapper.deleteBookCollection(bookCollect);
    }

}
