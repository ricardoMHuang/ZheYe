package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import zheye.server.entity.Author;
import zheye.server.mapper.AuthorMapper;
import zheye.server.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * (Author)实体类(Author)表服务实现类
 *
 * @author makejava
 * @since 2023-02-23 00:03:56
 */
@Service("authorService")
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {
    public List<Author> getAuthors(int[] authorId) {
        QueryWrapper<Author> queryWrapper = new QueryWrapper<>();
        List<Author> authors = new ArrayList<>();

        for (int i = 0; i < authorId.length; i++) {
            queryWrapper.eq("id", authorId[i]);
            if (ObjectUtils.isNotEmpty(baseMapper.selectList(queryWrapper)))
                authors.add(baseMapper.selectOne(queryWrapper));
            queryWrapper = new QueryWrapper<>();
        }
        return authors;
    }
}

