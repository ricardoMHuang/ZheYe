package zheye.server.service;

import zheye.server.entity.GroupType;
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
public interface IGroupTypeService extends IService<GroupType> {

    List<GroupType> getAllTypeItem();

    GroupType getGroupType(int typeId);
}
