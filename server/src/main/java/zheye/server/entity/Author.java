package zheye.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (Author)实体类
 *
 * @author makejava
 * @since 2023-02-22 23:38:46
 */

@TableName("author")
@Data
public class Author implements Serializable {
    private static final long serialVersionUID = -94117610027840604L;

    private Long id;

    private String name;

    private String sex;

    private String country;

    private String birthday;

    private String deathTime;

    private String briefIntroduction;

    private String image;


}

