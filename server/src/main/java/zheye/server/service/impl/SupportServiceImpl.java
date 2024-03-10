package zheye.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zheye.server.entity.BookComment;
import zheye.server.entity.Support;
import zheye.server.mapper.BookCommentMapper;
import zheye.server.mapper.SupportMapper;
import zheye.server.service.SupportService;
@Service("supportService")
public class SupportServiceImpl extends ServiceImpl<SupportMapper, Support> implements SupportService {

    @Override
    public Object insertSupport(Support support) {
        return baseMapper.insert(support);
    }

    @Override
    public Support select(Support support) {
        return baseMapper.MultiId(support);
    }

    @Override
    public Boolean deleteSupport(Support support) {
        return baseMapper.deleteByMutiId(support);
    }
}
