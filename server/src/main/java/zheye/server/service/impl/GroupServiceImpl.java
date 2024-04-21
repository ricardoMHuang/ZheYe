package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zheye.server.entity.Group;
import zheye.server.entity.User;
import zheye.server.mapper.GroupMapper;
import zheye.server.service.GroupService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("GroupService")
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
    @Value("${file.rootPath}")
    String ROOT_PATH;
    //图片存放根目录下的子目录
    @Value("${file.sonPath}")
    String SON_PATH;
    //访问域名
    @Value("${file.localPath}")
    String localPath;

    @Override
    public Group getGroup(int id) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Group> getGroupListByClass(int typeId) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", typeId).eq("state", 1);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public int addNumber(int groupId) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", groupId);
        Group group = baseMapper.selectOne(queryWrapper);
        UpdateWrapper<Group> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", groupId).set("number", group.getNumber() + 1);
        return baseMapper.update(null, updateWrapper);
    }

    @Override
    public int reduceNumber(int groupId) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", groupId);
        Group group = baseMapper.selectOne(queryWrapper);
        UpdateWrapper<Group> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", groupId).set("number", group.getNumber() - 1);
        return baseMapper.update(null, updateWrapper);
    }

    @Override
    public List<Group> selectList(List<Long> groupIdList) {
        return baseMapper.selectGroupsByUserIdList(groupIdList);
    }

    @Override
    public List<Group> selectByCreatorId(int creatorId) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator_id", creatorId).eq("state", 1);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public int disbandGroup(Group group) {
        UpdateWrapper<Group> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", group.getId()).set("state", 0).set("disband_reason", group.getDisbandReason());
        return baseMapper.update(null, updateWrapper);
    }

    @Override
    public int create(Group group) {
        group.setNumber(1);
        group.setState(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        group.setCreatTime(formatter.format(date));
        List<Long> idList = baseMapper.selectList(null).stream().map(Group::getId).collect(Collectors.toList());
        long max = idList.get(0);
        for (Long id : idList) {
            if (id > max) {
                max = id;
            }
        }
        group.setId(max + 1);
        return baseMapper.insert(group);
    }

    @Override
    public String uploadImg(MultipartFile file) {
        if (file.isEmpty()) {
            throw new NullPointerException("文件为空");
        }
        // 设置文件上传后的路径
        String filePath = ROOT_PATH + SON_PATH;
        // 获取文件名后缀名
        String suffix = file.getOriginalFilename();
        String prefix = suffix.substring(suffix.lastIndexOf(".") + 1);
        //为防止文件重名被覆盖，文件名取名为：当前日期 + 1-1000内随机数
        String fileName = "Group" + UUID.randomUUID() + "." + prefix;
        //创建文件路径
        System.out.println("fileName: " + fileName);
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            //假如文件不存在即重新创建新的文件已防止异常发生
            dest.getParentFile().mkdirs();
        }
        try {
            //transferTo（dest）方法将上传文件写到服务器上指定的文件
            file.transferTo(dest);
            //生成静态文件访问路径
            String filePathNew = localPath + fileName;
            System.out.println(filePathNew);
            return filePathNew;
        } catch (Exception e) {
            return dest.toString();
        }
    }

    @Override
    public boolean deleteImage(String filePath) {
        String fileName = filePath.replace(localPath, "");
        fileName = fileName.replace("\"", "");
        System.out.println(fileName);
        String fiPath = ROOT_PATH + SON_PATH;
        String fPath = fiPath + fileName;
        System.out.println(fPath);
        File file = new File(fPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return false;
        } else {
            file.delete();
            return true;
        }
    }
}
