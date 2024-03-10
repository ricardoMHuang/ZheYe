package zheye.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zheye.server.entity.Support;
import zheye.server.service.SupportService;
import zheye.server.utils.Result;

import javax.annotation.Resource;

@RestController
@RequestMapping("frontApi/support")
public class SupportController {
    @Resource
    private SupportService supportService;

    @PostMapping("/insertSupport")
    public Result insertSupport(@RequestBody Support support) {
        if (supportService.select(support) == null) {
            return Result.ok(supportService.insertSupport(support)).message("点赞成功");
        } else {
            return Result.error().message("已经点赞了");
        }
    }
    @PostMapping("/deleteSupport")
    public Result deleteSupport(@RequestBody Support support) {
        if (supportService.select(support) != null) {
            return Result.ok(supportService.deleteSupport(support)).message("取消点赞成功");
        } else {
            return Result.error().message("未点赞");
        }
    }
    @PostMapping("/select")
    public Result select(@RequestBody Support support) {
        if (supportService.select(support) != null) {
            return Result.ok(supportService.select(support)).message("已点赞");
        } else {
            return Result.error().message("未点赞");
        }
    }
}
