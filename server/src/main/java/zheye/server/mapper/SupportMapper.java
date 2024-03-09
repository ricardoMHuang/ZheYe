package zheye.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import zheye.server.entity.Support;

public interface SupportMapper extends BaseMapper<Support> {

    Support MultiId(Support support);

    Boolean deleteByMutiId(Support support);
}
