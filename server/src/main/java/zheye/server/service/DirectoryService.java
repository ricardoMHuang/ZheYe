package zheye.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zheye.server.entity.Directory;

import java.util.List;
import java.util.Map;

/**
 * 目录(Directory)表服务接口
 *
 * @author makejava
 * @since 2023-02-23 22:43:22
 */
public interface DirectoryService extends IService<Directory> {

    Directory getDirectoryItem(int id);

    Map<String, String> getBookChapter(int bookId);
}

