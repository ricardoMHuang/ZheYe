package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import zheye.server.entity.Post;
import zheye.server.mapper.PostMapper;
import zheye.server.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李嘉图
 * @since 2024-03-16
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    @Value("${file.rootPath}")
    String ROOT_PATH;
    //图片存放根目录下的子目录
    @Value("${file.sonPath}")
    String SON_PATH;
    //访问域名
    @Value("${file.localPath}")
    String localPath;

    @Override
    public String uploadImg(MultipartFile file) {
        if (file.isEmpty()) {
            throw new NullPointerException("文件为空");
        }
        // 设置文件上传后的路径
        String filePath = ROOT_PATH + SON_PATH;
        // 获取文件名后缀名
        String suffix = file.getOriginalFilename();
        String prefix = suffix.substring(suffix.lastIndexOf(".") + 1);
        //为防止文件重名被覆盖，文件名取名为：当前日期 + 1-1000内随机数
        String fileName = "post" + UUID.randomUUID() + "." + prefix;
        //创建文件路径
        System.out.println("fileName: " + fileName);
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            //假如文件不存在即重新创建新的文件已防止异常发生
            dest.getParentFile().mkdirs();
        }
        try {
            //transferTo（dest）方法将上传文件写到服务器上指定的文件
            file.transferTo(dest);
            //生成静态文件访问路径
            String filePathNew = localPath + fileName;
            System.out.println(filePathNew);
            return filePathNew;
        } catch (Exception e) {
            return dest.toString();
        }
    }

    @Override
    public boolean deleteImage(String filePath) {
        String fileName = filePath.replace(localPath, "");
        fileName = fileName.replace("\"", "");
        System.out.println(fileName);
        String fiPath = ROOT_PATH + SON_PATH;
        String fPath = fiPath + fileName;
        System.out.println(fPath);
        File file = new File(fPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return false;
        } else {
            file.delete();
            return true;
        }
    }

    @Override
    public int submitPost(Post post) {
        List<Post> postList = baseMapper.selectList(null);
        postList.sort((post1, post2) -> post2.getId() - post1.getId());
        post.setId(postList.get(0).getId() + 1);
        return baseMapper.insert(post);
    }

    @Override
    public List<Post> getPostItem(int groupId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", groupId);
        return baseMapper.selectList(queryWrapper);

    }

    @Override
    public Post getPost(int id) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Post> getPostByUserId(int creatorId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator_id", creatorId);
        return baseMapper.selectList(queryWrapper);
    }
}
