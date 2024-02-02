package zheye.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.io.Serializable;

/**
 * (Carousel)表实体类
 *
 * @author makejava
 * @since 2023-02-22 16:28:36
 */
@Data
@TableName("carousel")
public class Carousel implements Serializable {

    private String imageItem;

}

