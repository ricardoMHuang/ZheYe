package zheye.server.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author 李嘉图
 * @since 2024-03-16
 */
@Getter
@Setter
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String title;

    private LocalDateTime createTime;

    /**
     * 创建者id
     */
    private Long creatorId;

    /**
     * 封面
     */
    private String image;

    /**
     * 点赞数
     */
    private Integer supportNum;

    /**
     * 评论数量
     */
    private Integer commentsNum;

    /**
     * 所属小组
     */
    private Integer groupId;

    /**
     * 内容
     */
    private String content;


}
