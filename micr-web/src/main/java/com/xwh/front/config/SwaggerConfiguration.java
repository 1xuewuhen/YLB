package com.xwh.front.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 陈方银
 * @date 2023/6/21
 * @since 1.0
 */

@EnableSwagger2
@Configuration
@EnableSwaggerBootstrapUI
public class SwaggerConfiguration {

    // 创建Docket文档
    @Bean
    public Docket docket() {
        // 1、创建Docket对象
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        // 2、创建Api信息，接口文档的总体描述
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("金融项目")
                .version("1.0")
                .description("前后端分离项目的项目，前端Vue，后端spring boot + Dubbo分布式项目")
                .contact(new Contact("鳕鱼神教","https://www.baidu.com","1984451361@qq.com"))
                .license("Apache 2.0")
                .build();
        docket = docket.apiInfo(apiInfo);
        // 3、设置参与文档生成的包
        docket = docket.select().apis(RequestHandlerSelectors.basePackage("com.xwh.front.controller")).build();
        return docket;
    }
}
