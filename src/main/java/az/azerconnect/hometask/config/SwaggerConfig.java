package az.azerconnect.hometask.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin
@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    public Docket apiProject() {
        return new Docket(DocumentationType.SWAGGER_12)
                .select()
                .apis(RequestHandlerSelectors.basePackage("az.azerconnect"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("")
                .description("")
                .contact(new Contact("", "", ""))
                .version("1.0.0")
                .build();
    }
}
