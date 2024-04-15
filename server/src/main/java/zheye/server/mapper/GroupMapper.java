package zheye.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import zheye.server.entity.Book;
import zheye.server.entity.Group;

import java.util.List;

@Repository
public interface GroupMapper extends BaseMapper<Group> {
    List<Group> selectGroupsByUserIdList(List<Long> groupIdList);

}
