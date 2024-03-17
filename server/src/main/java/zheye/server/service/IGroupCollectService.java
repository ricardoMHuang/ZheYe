package zheye.server.service;

import zheye.server.entity.GroupCollect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李嘉图
 * @since 2024-03-13
 */
public interface IGroupCollectService extends IService<GroupCollect> {

    GroupCollect selectGroup(GroupCollect groupCollect);

    int insertGroupCollect(GroupCollect groupCollect);

    List<GroupCollect> selectGroupByUserId(long userId);

    int deleteGroupCollect(GroupCollect groupCollect);

    List<GroupCollect> selectByGroupId(int groupId);
}
