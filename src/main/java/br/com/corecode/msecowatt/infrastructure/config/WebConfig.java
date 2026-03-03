package br.com.corecode.msecowatt.infrastructure.config;

import br.com.corecode.msecowatt.infrastructure.annotations.ApiV1;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api/v1",
                beanType -> beanType.isAnnotationPresent(ApiV1.class)
                );
    }
}
