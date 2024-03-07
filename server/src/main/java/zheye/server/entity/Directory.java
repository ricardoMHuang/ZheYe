package zheye.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 目录(Directory)表实体类
 *
 * @author makejava
 * @since 2023-02-23 22:43:22
 */
@Data
@TableName("directory")
public class Directory extends Model<Directory> {
    //第一章
    private Integer id;

    private int bookId;

    private String chapter1;

    private String chapter2;

    private String chapter3;

    private String chapter4;

    private String chapter5;

    private String chapter6;

    private String chapter1Content;

    private String chapter2Content;

    private String chapter3Content;

    private String chapter4Content;

    private String chapter5Content;

    private String chapter6Content;


}

