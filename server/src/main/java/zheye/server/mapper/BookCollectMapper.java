package zheye.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import zheye.server.entity.BookCollect;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hhw
 * @since 2022-12-06
 */
@Repository
public interface BookCollectMapper extends BaseMapper<BookCollect> {

     boolean deleteBookCollection(BookCollect bookCollect);

     boolean addBookCollection(BookCollect bookCollect);

}
