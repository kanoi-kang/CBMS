package it.xdu.xzk.citybusmanagersystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 将根路径映射到登录页面
        registry.addViewController("/").setViewName("login");
        // 如果需要，还可以添加其他路径映射
    }
}
