package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import zheye.server.entity.Directory;
import zheye.server.mapper.DirectoryMapper;
import zheye.server.service.DirectoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 目录(Directory)表服务实现类
 *
 * @author makejava
 * @since 2023-02-23 22:43:22
 */
@Service("directoryService")
public class DirectoryServiceImpl extends ServiceImpl<DirectoryMapper, Directory> implements DirectoryService {

    @Override
    public Directory getDirectoryItem(int id) {
        QueryWrapper<Directory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id", id);
        if (ObjectUtils.isNotEmpty(baseMapper.selectOne(queryWrapper))) {

            System.out.print(baseMapper.selectOne(queryWrapper));
            return baseMapper.selectOne(queryWrapper);
        } else {
            return null;
        }
    }
}

