package zheye.server.controller;


import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import zheye.server.entity.Post;
import zheye.server.service.IPostService;
import zheye.server.utils.Result;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李嘉图
 * @since 2024-03-16
 */
@RestController
@RequestMapping("/frontApi/post")
public class PostController {
    @Resource
    IPostService iPostService;

    @PostMapping("/upLoad")
    public Result upLoadImage(@RequestParam("file") MultipartFile img) {
        String uploadImg = iPostService.uploadImg(img);
        return Result.ok(uploadImg).message("上传成功");
    }

    @PostMapping("/getPostItem")
    public Result getPostItem(@RequestBody int groupId) {
        List<Post> postItem = iPostService.getPostItem(groupId);
        if (postItem != null) {
            return Result.ok(postItem).message("获取帖子成功");
        } else {
            return Result.ok().message("该小组无帖子");
        }
    }

    @PostMapping("/getPostByUserId")
    public Result getPostByUserId(@RequestBody int creatorId) {
        List<Post> postItem = iPostService.getPostByUserId(creatorId);
        if (postItem != null) {
            return Result.ok(postItem).message("获取帖子成功");
        } else {
            return Result.ok().message("该小组无帖子");
        }
    }

    @PostMapping("/getPost")
    public Result getPost(@RequestBody int id) {
        Post post = iPostService.getPost(id);
        if (post != null) {
            return Result.ok(post).message("获取本帖子成功");
        } else {
            return Result.ok().message("该帖子为空");
        }
    }

    @PostMapping("/deleteImage")
    public Result deleteImage(@RequestBody String filePath) {
        boolean flag = iPostService.deleteImage(filePath);
        if (flag) {
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("不存在");
        }
    }

    @PostMapping("/submitPost")
    public Result submitPost(@RequestBody Post post) {
        int flag = iPostService.submitPost(post);
        if (flag != 0) {
            return Result.ok().message("发布成功");
        } else {
            return Result.error().message("发布失败");
        }
    }
}
