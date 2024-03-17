package zheye.server.service;

import org.springframework.web.multipart.MultipartFile;
import zheye.server.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李嘉图
 * @since 2024-03-16
 */
public interface IPostService extends IService<Post> {

    String uploadImg(MultipartFile file);

    boolean deleteImage(String filePath);

    int submitPost(Post post);

    List<Post> getPostItem(int groupId);

    Post getPost(int id);

    List<Post> getPostByUserId(int creatorId);
}
