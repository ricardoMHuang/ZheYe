package zheye.server.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import zheye.server.entity.GroupType;
import zheye.server.service.IGroupTypeService;
import zheye.server.utils.Result;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李嘉图
 * @since 2024-03-13
 */
@RestController
@RequestMapping("/frontApi/group-type")
public class GroupTypeController {
    @Resource
    private IGroupTypeService iGroupTypeService;

    @PostMapping("/getAllTypeItem")
    public Result getAllTypeItem() {
        return Result.ok(iGroupTypeService.getAllTypeItem()).message("返回所有类型");
    }

    @PostMapping("/getGroupType")
    public Result getGroupType(@RequestBody int typeId) {
        GroupType groupType = iGroupTypeService.getGroupType(typeId);
        if (groupType != null)
            return Result.ok(groupType).message("存在类型");
        else
            return Result.error().message("不存在该类型");
    }
}
