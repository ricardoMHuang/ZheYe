package zheye.server.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (BookComment)表实体类
 *
 * @author makejava
 * @since 2023-02-24 14:30:06
 */
@SuppressWarnings("serial")
@Data
@TableName("book_comment")
public class BookComment extends Model<BookComment> {
    @TableId(value = "Id", type = IdType.AUTO)

    private Integer id;

    private String content;

    private Long bookId;

    private Long userId;

    private String publishTime;

    private Float rate;

    private int commentId;

    private int supportNum;


}

