package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import zheye.server.entity.Carousel;
import zheye.server.mapper.CarouselMapper;
import zheye.server.service.CarouselService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Carousel)表服务实现类
 *
 * @author makejava
 * @since 2023-02-22 16:28:45
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {
    @Override
    public List<Carousel> getCarouselItem() {
        QueryWrapper<Carousel> queryWrapper = new QueryWrapper<>();
        System.out.print(baseMapper.selectList(queryWrapper));
        return baseMapper.selectList(queryWrapper);
    }
}

