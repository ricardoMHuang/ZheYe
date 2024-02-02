package zheye.server.controller;

import zheye.server.service.CarouselService;
import org.springframework.web.bind.annotation.*;

import zheye.server.utils.Result;

import javax.annotation.Resource;

/**
 * (Carousel)表控制层
 *
 * @author makejava
 * @since 2023-02-22 16:28:36
 */
@RestController
@RequestMapping("/frontApi/book")
public class CarouselController {
    @Resource
    private CarouselService carouselService;

    @PostMapping("/imageUrl")
    public Result imageUrl() {
        return Result.ok(carouselService.getCarouselItem());
    }
}

