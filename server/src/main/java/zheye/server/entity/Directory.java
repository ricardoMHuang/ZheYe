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
    private String chapterOne;

    private int bookId;

    private String chapterTwo;

    private String chapterThree;

    private Integer id;

    private String chapterFour;

    private String chapterFive;

    private String chapterSix;


}

