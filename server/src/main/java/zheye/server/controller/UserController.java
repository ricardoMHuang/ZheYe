package zheye.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zheye.server.entity.Book;
import zheye.server.entity.BookCollect;
import zheye.server.entity.User;
import zheye.server.service.UserService;
import zheye.server.utils.Result;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/frontApi/user")
public class UserController {
    @Resource
    private UserService userService;


    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        boolean exists = userService.checkLogin(user);
        if (exists) {
            User user1 = userService.findUserList(user);
            return Result.ok(user1).message("登录成功！");
        }
        return Result.error().message("用户名或密码错误");
    }

    @PostMapping("/registerEmail")
    public Result sentEmail(@RequestBody String email) {
        System.out.print(email);
        String data = userService.sentEmail(email);
        return Result.ok(data);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        Boolean exist = userService.registerJudge(user);
        if (exist) {
            return Result.exist().message("用户名或邮箱已被注册");
        }
        if (userService.save(user)) {
            return Result.ok().message("注册成功！");
        }
        return Result.error().message("注册失败");
    }

    @PostMapping("/getReplyer")
    public Result getReplyerItem(@RequestBody int id) {
        return Result.ok(userService.getReplyerSet(id)).message("获取回复人信息成功");
    }

    @PostMapping("/updateInfo")
    public Result updateInfo(@RequestBody User user) {
        userService.updateUser(user);
        return Result.ok().message("用户更新成功");
    }

    @PostMapping("/selectNickname")
    public Result selectNickname(@RequestBody int id) {
        if (userService.selectNickname(id) != null)
            return Result.ok(userService.selectNickname(id)).message("搜索成功");
        else {
            return Result.error().message("该用户不存在");
        }
    }
}
