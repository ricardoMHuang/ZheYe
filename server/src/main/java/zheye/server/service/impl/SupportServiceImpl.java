package zheye.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zheye.server.entity.Support;
import zheye.server.mapper.SupportMapper;
import zheye.server.service.SupportService;

import java.util.Comparator;
import java.util.List;

@Service("supportService")
public class SupportServiceImpl extends ServiceImpl<SupportMapper, Support> implements SupportService {

    @Override
    public Object insertSupport(Support support) {
        List<Support> list = baseMapper.selectList(null);
        list.sort((support1, support2) -> (int) (support2.getId() - support2.getId()));
        support.setId(list.get(0).getId() + 1);
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
