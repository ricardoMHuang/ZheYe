package zheye.server.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zheye.server.entity.Group;
import zheye.server.entity.GroupCollect;
import zheye.server.service.GroupService;
import zheye.server.service.IGroupCollectService;
import zheye.server.utils.Result;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("frontApi/group")
public class GroupController {
    @Resource
    private GroupService groupService;

    @Resource
    private IGroupCollectService iGroupCollectService;

    @PostMapping("/getGroup")
    public Result getGroup(@RequestBody int id) {
        Group group = groupService.getGroup(id);
        if (group != null) {
            if (group.getState() != 0)
                return Result.ok(group).message("查询该小组成功");
            else
                return Result.error().message(group.getDisbandReason());
        } else {
            return Result.error().message("该小组不存在");
        }
    }

    @PostMapping("/disbandGroup")
    public Result disbandGroup(@RequestBody Group group) {
        int affectRows = groupService.disbandGroup(group);
        if (affectRows == 1) {
            return Result.ok().message("解散该小组成功");
        } else {
            return Result.error().message("解散该小组失败");
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

    @PostMapping("/getUJGroupList")
    public Result getGroupList(@RequestBody int userId) {
        List<GroupCollect> groupCollectList = iGroupCollectService.selectGroupByUserId(userId);
        List<Long> groupIdList = groupCollectList.stream().map(GroupCollect::getGroupId).collect(Collectors.toList());
        List<Group> groupList = groupService.selectList(groupIdList);
        if (groupList != null) {
            return Result.ok(groupList).message("查询用户的小组成功");
        } else {
            return Result.error().message("查询小组失败");
        }
    }

    @PostMapping("/getUCGroupList")
    public Result getUCGroupList(@RequestBody int userId) {
        List<Group> groupList = groupService.selectByCreatorId(userId);
        if (groupList != null) {
            return Result.ok(groupList).message("查询用户创建的小组成功");
        } else {
            return Result.error().message("查询小组失败");
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

    @PostMapping("/upLoadImage")
    public Result upLoadImage(@RequestParam("file") MultipartFile img) {
        String uploadImg = groupService.uploadImg(img);
        return Result.ok(uploadImg).message("上传成功");
    }

    @PostMapping("/deleteImage")
    public Result deleteImage(@RequestBody String filePath) {
        boolean flag = groupService.deleteImage(filePath);
        if (flag) {
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("不存在");
        }
    }

    @PostMapping("/create")
    public Result create(@RequestBody Group group) {
        int updateNumberFlag = groupService.create(group);
        GroupCollect groupCollect = new GroupCollect();
        groupCollect.setGroupId(group.getId());
        groupCollect.setUserId(group.getCreatorId());
        LocalDateTime now = LocalDateTime.now();
        groupCollect.setCollectTime(now);
        iGroupCollectService.insertGroupCollect(groupCollect);
        if (updateNumberFlag == 1) {
            return Result.ok(true).message("小组创建成功");
        } else {
            return Result.error().message("小组创建失败");
        }
    }

}
