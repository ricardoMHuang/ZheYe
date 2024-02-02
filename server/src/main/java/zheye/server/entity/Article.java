package zheye.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (Article)表实体类
 *
 * @author makejava
 * @since 2023-02-23 20:19:56
 */

@Data
@TableName("article")
public class Article extends Model<Article> {

    private Long id;

    private String title;

    private String content;

    private String publicTime;
    private String userName;

    private String userImage;
    private String bookImage;
    private Long bookId;
    private Long userId;


}

