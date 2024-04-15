package zheye.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String path = "D:\\ZheYeProject\\trunk\\Project\\server\\src\\main\\resources\\";//映射的本地路径
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + path);

    }
}
