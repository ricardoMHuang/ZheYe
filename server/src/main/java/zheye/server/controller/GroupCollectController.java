package zheye.server.controller;

import org.springframework.web.bind.annotation.*;
import zheye.server.entity.GroupCollect;
import zheye.server.service.IGroupCollectService;
import zheye.server.utils.Result;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李嘉图
 * @since 2024-03-13
 */
@RestController
@RequestMapping("/frontApi/groupCollect")
public class GroupCollectController {
    @Resource
    IGroupCollectService iGroupCollectService;

    @PostMapping("/selectGroup")
    public Result selectGroup(@RequestBody GroupCollect groupCollect) {
        GroupCollect groupCollect1 = iGroupCollectService.selectGroup(groupCollect);
        if (groupCollect1 != null) {
            return Result.ok(groupCollect1).message("该用户收藏了此小组");
        } else {
            return Result.error().message("未收藏该小组");
        }
    }

    @PostMapping("/insertGroupCollect")
    public Result insertGroupCollect(@RequestBody GroupCollect groupCollect) {
        int flag = iGroupCollectService.insertGroupCollect(groupCollect);
        if (flag != Integer.MIN_VALUE + 1001) {
            return Result.ok(true).message("收藏成功");
        } else {
            return Result.error().message("收藏失败");
        }
    }

    @PostMapping("/selectGroupByUserId")
    public Result selectGroupByUserId(@RequestBody long userId) {
        List<GroupCollect> groupCollectList = iGroupCollectService.selectGroupByUserId(userId);
        if (groupCollectList != null) {
            return Result.ok(groupCollectList).message("收藏的小组查询成功");
        } else {
            return Result.error().message("收藏的小组为空");
        }

    }

    @PostMapping("/deleteGroupCollect")
    public Result deleteGroupCollect(@RequestBody GroupCollect groupCollect) {
        int flag = iGroupCollectService.deleteGroupCollect(groupCollect);
        if (flag == 1) {
            return Result.ok(true).message("退出成功");
        } else {
            return Result.error().message("退出失败");
        }
    }

    @PostMapping("/selectByGroupId")
    public Result selectByGroupId(@RequestBody int groupId) {
        List<GroupCollect> groupCollectList = iGroupCollectService.selectByGroupId(groupId);
        if (groupCollectList != null) {
            return Result.ok(groupCollectList).message("小组成员id查询成功");
        } else {
            return Result.error().message("小组成员为空");
        }

    }
}
