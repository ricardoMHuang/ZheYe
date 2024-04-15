package zheye.server.service;

import zheye.server.entity.Group;

import java.util.List;

public interface GroupService {

    Group getGroup(int id);

    List<Group> getGroupListByClass(int typeId);

    int addNumber(int groupId);

    int reduceNumber(int groupId);

    List<Group> selectList(List<Long> groupIdList);
}
