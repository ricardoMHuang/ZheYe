package zheye.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zheye.server.entity.Support;

public interface SupportService extends IService<Support> {
    Object insertSupport(Support support);

    Support select(Support support);

    Object deleteSupport(Support support);
}
