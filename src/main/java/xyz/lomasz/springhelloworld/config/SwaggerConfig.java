package xyz.lomasz.springhelloworld.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket SwaggerApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("xyz.lomasz.springhelloworld"))
        .paths(regex(".*?"))
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Spring Hello World's Swagger")
        .description("Swagger for Spring Hello World Project")
        .contact(new Contact("Lukasz Tomaszewski",
            "http://sieni.us/?id=40",
            "malpa@malpa.pl"))
        .license("Only for Awesome Peoples")
        .build();
  }
}
