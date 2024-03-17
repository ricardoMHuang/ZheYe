package zheye.server;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class FastAutoGeneratorTest {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/zhe-ye?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                        "root", "111111")
                .globalConfig(builder -> {
                    builder.author("李嘉图") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://ZheYeProject//trunk//Project//server//src//main//java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("zheye.server") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .mapper("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://ZheYeProject//trunk//Project//server//src//main//resources//Mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("post") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_")// 设置过滤表前缀
                            .controllerBuilder()
                            .enableRestStyle()
                            .entityBuilder()
                            .enableLombok();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}