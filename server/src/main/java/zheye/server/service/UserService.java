package zheye.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zheye.server.entity.Book;
import zheye.server.entity.BookCollect;
import zheye.server.entity.User;

import java.util.List;

@Service
@Transactional
public interface UserService extends IService<User> {

    User findUserList(User user);

    String sentEmail(String email);

    Boolean registerJudge(User user);

    Boolean checkLogin(User user);


    User getReplyerSet(int id);

    void updateUser(User user);

    String selectNickname(int id);
}
