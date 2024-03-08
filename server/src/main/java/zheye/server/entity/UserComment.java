package zheye.server.entity;

import lombok.Data;

@Data
public class UserComment {
    private Integer id;

    private String content;

    private Integer bookId;

    private Integer userId;

    private String publishTime;

    private Float rate;

    private int commentId;

    private int supportNum;

    private String avatar;

    private String nickname;

}
