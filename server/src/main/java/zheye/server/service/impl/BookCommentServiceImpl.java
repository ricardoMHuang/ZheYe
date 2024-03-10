package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import zheye.server.entity.BookComment;
import zheye.server.entity.User;
import zheye.server.entity.UserComment;
import zheye.server.mapper.BookCommentMapper;
import zheye.server.service.BookCommentService;
import org.springframework.stereotype.Service;
import zheye.server.utils.ReflectUtil;

import javax.xml.stream.events.Comment;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * (BookComment)表服务实现类
 *
 * @author makejava
 * @since 2023-02-24 14:30:16
 */
@Service("bookCommentService")
public class BookCommentServiceImpl extends ServiceImpl<BookCommentMapper, BookComment> implements BookCommentService {

    @Override
    public void commentSet(BookComment comment) {
        baseMapper.insert(comment);
    }

    @Override
    public Object getComment(int bookId) throws InvocationTargetException, IllegalAccessException {

        List<UserComment> commentList = baseMapper.selectUserAndCommentList(bookId);
        System.out.println("_________________________________");
//        System.out.println(commentList);
//        按点赞数将评论排序
        commentList.sort(new Comparator<UserComment>() {
            @Override
            public int compare(UserComment bookComment1, UserComment bookComment2) {
                return bookComment2.getSupportNum() - bookComment1.getSupportNum();
            }
        });
        List<UserComment> parentCommentList = new ArrayList<>();
        List<UserComment> allChildCommentList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();//用于记录父子评论的关系
        //分离父子评论，初始化map
        for (UserComment userComment : commentList) {
            if (userComment.getCommentId() == 0) {
                parentCommentList.add(userComment);
            } else {
                allChildCommentList.add(userComment);
            }
            map.put(userComment.getId(), userComment.getCommentId());
        }
        System.out.println();
        System.out.println(parentCommentList);
        System.out.println();
        System.out.println(allChildCommentList);
        System.out.println();
        System.out.println(map);
        //更新map
        for (UserComment userComment : commentList) {
            int parentId = userComment.getCommentId();
            int id = userComment.getId();
            if (parentId != 0) {
                int oldId = id;
                while (map.get(id) != 0) {
                    id = map.get(id);
                }
                parentId = id;
                map.put(oldId, parentId);
            }
        }
        System.out.println();
        System.out.println(map);
        List<Object> obj = new ArrayList<>();
        Map<String, Object> propertiesMap = new HashMap<>();
        for (UserComment parentComment : parentCommentList) {
            List<UserComment> childCommentList = new ArrayList<>();
            for (UserComment childComment : allChildCommentList) {
                int id = childComment.getId();
                int parentId = map.get(id);
                if (parentComment.getId() == parentId && parentId != 0) {
                    childCommentList.add(childComment);
                }
            }
            propertiesMap.put("childCommentList", childCommentList);
            obj.add(ReflectUtil.getObject(parentComment, propertiesMap));
        }
        System.out.println(obj);
//        for (int i = 0; i < commentList.toArray().length; i++) {
//            List<UserComment> childComment = new ArrayList<>();
//            for (int j = 0; j < commentList.toArray().length; j++) {
//                if (i != j && commentList.get(j).getCommentId() != 0 && commentList.get(i).getId() == commentList.get(j).getCommentId() && commentList.get(i).getCommentId() == 0) {
//                    childComment.add(commentList.get(j));
//                }
//            }
//            if (commentList.get(i).getCommentId() == 0) {
//                System.out.println("childComment " + childComment);
//                propertiesMap.put("childComment", childComment);
//                obj.add(ReflectUtil.getObject(commentList.get(i), propertiesMap));
//            }
//        }
//        System.out.println();
//        System.out.println(obj);
        return obj;
    }

    @Override
    public int updateComment(BookComment comment) {
        QueryWrapper<BookComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", comment.getCommentId());
        System.out.println("受影响行数：" + baseMapper.updateById(comment));
        return baseMapper.updateById(comment);
    }

    @Override
    public BookComment getCommentById(int id) {
        QueryWrapper<BookComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return baseMapper.selectOne(queryWrapper);
    }


}

