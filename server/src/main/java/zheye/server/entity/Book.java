package zheye.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (Book)实体类
 *
 * @author makejava
 * @since 2023-02-22 23:41:43
 */
@TableName("book")
@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 683645577682435956L;

    private Long id;
    /**
     * 作品名称
     */
    private String name;
    /**
     * 评分
     */
    private Float score;
    /**
     * 简介
     */
    private String briefIntroduction;
    /**
     * 作者编号
     */
    private Long authorId;

    private Integer page;

    private Integer articleId;

    private Integer numberOfReader;

    private String image;
    /**
     * 出版社
     */
    private String publish;

    private int typeId;


}

