package zheye.server.mapper;

import org.springframework.stereotype.Repository;
import zheye.server.entity.Author;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hhw
 * @since 2022-12-06
 */
@Repository
public interface AuthorMapper extends BaseMapper<Author> {

    Author getAuthorById(int authorId);
}
