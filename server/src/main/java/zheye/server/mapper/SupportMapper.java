package zheye.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import zheye.server.entity.Support;
public interface SupportMapper extends BaseMapper<Support> {

    Support MultiId(Support support);

    Boolean deleteByMutiId(Support support);
}
