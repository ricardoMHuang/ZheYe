package zheye.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import zheye.server.service.BookCollectService;
import zheye.server.service.BookService;
import zheye.server.service.impl.BookCollectServiceImpl;
import zheye.server.service.impl.BookServiceImpl;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("front_User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String password;

    private String realName;

    private String address;

    private String phone;

    private String age;

    private String email;

    private String role;

    private String updateTime;

    private Integer isDelete;

    private String code;

    private String avatar;

    private String account;

    private String area;

    private Date createDate;

    private String nickname;

    private String sex;

    private String work;

    private String hobby;

    private String design;


}