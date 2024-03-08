package zheye.server.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import zheye.server.entity.Directory;

import java.util.List;
import java.util.Map;

public interface DirectoryMapper extends BaseMapper<Directory> {


    Map<String, String> selectChapter(int bookId);
}
