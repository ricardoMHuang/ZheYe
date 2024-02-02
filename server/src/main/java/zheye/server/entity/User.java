package zheye.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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