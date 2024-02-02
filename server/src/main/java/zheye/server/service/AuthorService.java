package zheye.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zheye.server.entity.Author;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * (Author)实体类(Author)表服务接口
 *
 * @author makejava
 * @since 2023-02-23 00:03:55
 */
public interface AuthorService extends IService<Author> {

    List<Author> getAuthors(int[] authorId);
}

