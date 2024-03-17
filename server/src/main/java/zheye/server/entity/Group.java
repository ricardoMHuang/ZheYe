package zheye.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;


@Data
@TableName("all_group")
public class Group {
    private Integer id;
    private String image;
    private String brief;
    private String name;
    private int state;
    private int typeId;
    private int number;
    private String creatTime;
    private int creatorId;
}
