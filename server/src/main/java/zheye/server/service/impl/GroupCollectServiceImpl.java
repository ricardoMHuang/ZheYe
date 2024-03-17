package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.models.auth.In;
import zheye.server.entity.GroupCollect;
import zheye.server.mapper.GroupCollectMapper;
import zheye.server.service.IGroupCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李嘉图
 * @since 2024-03-13
 */
@Service
public class GroupCollectServiceImpl extends ServiceImpl<GroupCollectMapper, GroupCollect> implements IGroupCollectService {

    @Override
    public GroupCollect selectGroup(GroupCollect groupCollect) {
        QueryWrapper<GroupCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", groupCollect.getUserId())
                .eq("group_id", groupCollect.getGroupId());
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public int insertGroupCollect(GroupCollect groupCollect) {
        return baseMapper.insert(groupCollect);
    }

    @Override
    public List<GroupCollect> selectGroupByUserId(long userId) {
        QueryWrapper<GroupCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public int deleteGroupCollect(GroupCollect groupCollect) {
        Map<String, Object> map = new HashMap<>();
        map.put("group_id", groupCollect.getGroupId());
        map.put("user_id", groupCollect.getUserId());
        return baseMapper.deleteByMap(map);
    }

    @Override
    public List<GroupCollect> selectByGroupId(int groupId) {
        QueryWrapper<GroupCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", groupId);
        return baseMapper.selectList(queryWrapper);
    }

}
