package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import zheye.server.entity.Book;
import zheye.server.entity.BookComment;
import zheye.server.entity.UserComment;
import zheye.server.mapper.BookCommentMapper;
import zheye.server.service.BookCommentService;
import org.springframework.stereotype.Service;
import zheye.server.utils.ReflectUtil;

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
    public Object getComment(int id) throws InvocationTargetException, IllegalAccessException {

        List<UserComment> commentList = baseMapper.selectUserAndCommentList(id);
        System.out.println("_________________________________");
        System.out.println(commentList.toArray().length);
        System.out.println(commentList);
//        按点赞数将评论排序
        commentList.sort(new Comparator<UserComment>() {
            @Override
            public int compare(UserComment bookComment1, UserComment bookComment2) {
                return bookComment2.getSupportNum() - bookComment1.getSupportNum();
            }
        });
        Map<String, Object> propertiesMap = new HashMap<>();
        List<Object> obj = new ArrayList<>();
        for (int i = 0; i < commentList.toArray().length; i++) {
            List<UserComment> childComment = new ArrayList<>();
            for (int j = i + 1; j < commentList.toArray().length; j++) {
                if (commentList.get(j).getCommentId() != 0 && commentList.get(i).getId() == commentList.get(j).getCommentId() && commentList.get(i).getCommentId() == 0) {
                    childComment.add(commentList.get(j));
                }
            }
            if (commentList.get(i).getCommentId() == 0) {
                propertiesMap.put("childComment", childComment);
                obj.add(ReflectUtil.getObject(commentList.get(i), propertiesMap));
            }
        }
        System.out.println();
        System.out.println(obj);
        return obj;
    }
}

