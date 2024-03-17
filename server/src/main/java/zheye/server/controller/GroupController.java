package zheye.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zheye.server.entity.Group;
import zheye.server.service.GroupService;
import zheye.server.utils.Result;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("frontApi/group")
public class GroupController {
    @Resource
    private GroupService groupService;

    @PostMapping("/getGroup")
    public Result getGroup(@RequestBody int id) {

        Group group = groupService.getGroup(id);
        if (group != null) {
            return Result.ok(group).message("查询该小组成功");
        } else {
            return Result.error().message("查询该小组失败");
        }
    }

    @PostMapping("/getGroupListByClass")
    public Result getGroupListByClass(@RequestBody int typeId) {

        List<Group> groupList = groupService.getGroupListByClass(typeId);
        if (groupList != null) {
            return Result.ok(groupList).message("查询所有小组成功");
        } else {
            return Result.error().message("查询所有小组失败");
        }
    }

    @PostMapping("/addNumber")
    public Result addNumber(@RequestBody int groupId) {

        int updateNumberFlag = groupService.addNumber(groupId);
        if (updateNumberFlag == 1) {
            return Result.ok(true).message("组员人数更新成功");
        } else {
            return Result.error().message("组员人数更新失败");
        }
    }

    @PostMapping("/reduceNumber")
    public Result reduceNumber(@RequestBody int groupId) {
        int updateNumberFlag = groupService.reduceNumber(groupId);
        if (updateNumberFlag == 1) {
            return Result.ok(true).message("组员人数更新成功");
        } else {
            return Result.error().message("组员人数更新失败");
        }
    }
}
