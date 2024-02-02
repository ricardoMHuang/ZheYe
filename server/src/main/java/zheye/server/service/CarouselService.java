package zheye.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zheye.server.entity.Carousel;

import java.util.List;

/**
 * (Carousel)表服务接口
 *
 * @author makejava
 * @since 2023-02-22 16:28:45
 */
public interface CarouselService extends IService<Carousel> {
    List<Carousel> getCarouselItem();

}

