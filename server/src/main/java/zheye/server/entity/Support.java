package zheye.server.entity;

import lombok.Data;

@Data
public class Support {
    private Long userId;
    private int supportCommentId;

    private Long id;

    @Override
    public String toString() {
        return "Support{" + "id=" + id + ",supportCommentId=" + supportCommentId + ",userId=" + userId + "}";
    }
}
