package zheye.server.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (BookType)表实体类
 *
 * @author makejava
 * @since 2023-02-24 12:56:51
 */
@SuppressWarnings("serial")
@Data
@TableName("book_type")
public class BookType implements Serializable {

    private Integer id;

    private String type;

    private Integer totalQuantity;

}

