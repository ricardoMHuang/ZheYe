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
@TableName("bookCollect")
public class BookCollect implements Serializable {
    @TableId(value = "Id", type = IdType.AUTO)

    private Long id;
    private int bookId;
    private static final long serialVersionUID = 1L;

    private int userId;

    private String imageUrl;

    private String author;

    private Date createTime;

    private String detail;

    private String bookName;

    private String country;
    //是否在书架中
    private boolean state;
}
