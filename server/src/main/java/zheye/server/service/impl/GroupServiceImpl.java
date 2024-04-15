package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zheye.server.entity.Group;
import zheye.server.entity.User;
import zheye.server.mapper.GroupMapper;
import zheye.server.service.GroupService;

import java.util.List;

@Service("GroupService")
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    @Override
    public Group getGroup(int id) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Group> getGroupListByClass(int typeId) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", typeId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public int addNumber(int groupId) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", groupId);
        Group group = baseMapper.selectOne(queryWrapper);
        UpdateWrapper<Group> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", groupId).set("number", group.getNumber() + 1);
        return baseMapper.update(null, updateWrapper);
    }

    @Override
    public int reduceNumber(int groupId) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", groupId);
        Group group = baseMapper.selectOne(queryWrapper);
        UpdateWrapper<Group> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", groupId).set("number", group.getNumber() - 1);
        return baseMapper.update(null, updateWrapper);
    }

    @Override
    public List<Group> selectList(List<Long> groupIdList) {
        return baseMapper.selectGroupsByUserIdList(groupIdList);
    }

}
