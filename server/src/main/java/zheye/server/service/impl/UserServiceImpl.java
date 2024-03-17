package zheye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import zheye.server.entity.Book;
import zheye.server.entity.BookCollect;
import zheye.server.entity.User;
import zheye.server.mapper.UserMapper;
import zheye.server.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    @Override
    public String sentEmail(String email) {
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件设置值
        simpleMailMessage.setSubject("注册验证码");//邮件主题
        simpleMailMessage.setText("您的验证码为" + checkCode);//邮件内容
        simpleMailMessage.setTo(email);//邮件发给谁
        simpleMailMessage.setFrom(from); //邮件来自于谁
        mailSender.send(simpleMailMessage);
        return checkCode;
    }

    @Override
    public Boolean registerJudge(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!ObjectUtils.isEmpty(user.getEmail()), "email", user.getEmail());
        return baseMapper.exists(queryWrapper);
    }

    @Override
    public Boolean checkLogin(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq(!ObjectUtils.isEmpty(user.getEmail()), "email", user.getEmail());
        User arr1 = baseMapper.selectOne(queryWrapper);
//        System.out.println(arr1);
        queryWrapper.eq(!ObjectUtils.isEmpty(user.getPassword()), "password", user.getPassword());
        User arr = baseMapper.selectOne(queryWrapper);
//        System.out.println(arr);
        return baseMapper.exists(queryWrapper);

    }

    @Override
    public User getReplyerSet(int id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public void updateUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user.getId());
        baseMapper.update(user, queryWrapper);
    }

    @Override
    public String selectNickname(int id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return baseMapper.selectNickname(id);
    }

    @Override
    public User getUserInfo(long userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public User findUserList(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!ObjectUtils.isEmpty(user.getEmail()), "email", user.getEmail());
//        System.out.print(baseMapper.selectMaps(queryWrapper));
        return baseMapper.selectOne(queryWrapper);
    }

}
