package zheye.server.service;

import org.springframework.web.multipart.MultipartFile;
import zheye.server.entity.Group;

import java.util.List;

public interface GroupService {

    Group getGroup(int id);

    List<Group> getGroupListByClass(int typeId);

    int addNumber(int groupId);

    int reduceNumber(int groupId);

    List<Group> selectList(List<Long> groupIdList);

    List<Group> selectByCreatorId(int creatorId);

    int disbandGroup(Group group);

    int create(Group group);

    String uploadImg(MultipartFile img);

    boolean deleteImage(String filePath);
}
