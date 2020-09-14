package com.lead.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mac
 */
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket createRestApi(ApiInfo apiInfo) {
        //添加head参数配置start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                // 扫描控制器
                .apis(RequestHandlerSelectors.basePackage("com.lead.controller"))
                .paths(PathSelectors.any())
                .build().
                        apiInfo(apiInfo).globalOperationParameters(pars)
                ;
    }

    @Bean
    public ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot整合Swagger")
                .description("SpringBoot整合Swagger，详细信息......")
                .version("9.0")
                .contact(new Contact("lead", "www.baidu.com", "zp17719807219@163.com"))
                .license("The Lead License")
                .licenseUrl("http://www.baidu.com")
                .build();
    }

}
