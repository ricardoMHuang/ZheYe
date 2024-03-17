package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import zheye.server.entity.GroupType;
import zheye.server.mapper.GroupTypeMapper;
import zheye.server.service.IGroupTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李嘉图
 * @since 2024-03-13
 */
@Service
public class GroupTypeServiceImpl extends ServiceImpl<GroupTypeMapper, GroupType> implements IGroupTypeService {

    @Override
    public List<GroupType> getAllTypeItem() {
//        System.out.println("********************************");
//        System.out.println(baseMapper.selectList(null));
        return baseMapper.selectList(null);
    }

    @Override
    public GroupType getGroupType(int typeId) {
        QueryWrapper<GroupType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", typeId);
        return baseMapper.selectOne(queryWrapper);
    }
}
