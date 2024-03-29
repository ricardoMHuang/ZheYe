package zheye.server.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.springframework.stereotype.Repository;
import zheye.server.entity.User;

@Repository
public interface UserMapper extends BaseMapper<User> {

    String selectNickname(int id);
}