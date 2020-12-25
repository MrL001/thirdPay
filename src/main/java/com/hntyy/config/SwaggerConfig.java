package com.hntyy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())        //这个方法的作用（生成接口的时候页面显示的信息）
                .select()         //表示的是选择那些路径和API生成文档
                .apis(RequestHandlerSelectors.basePackage("com.hntyy.controller"))           //告诉他要扫描的接口存在的这个包
                .paths(PathSelectors.any())          //对所有的API进行监控
                .build();         //构建
    }

    /**
     * 这个方法主要的作用是显示页面上的信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("文档的标题")    //文档的标题
                .description("文档的描述")      //文档的描述
                .contact("lsj")                        //作者
                .version("v1.0")                          //版本
                .build();
    }
}