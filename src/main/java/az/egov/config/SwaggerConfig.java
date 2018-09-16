package az.egov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by admin on 05.09.2018.
 */

@EnableSwagger2
@Configuration
public class SwaggerConfig {


    private Contact contact = new Contact("Samir Osmanov",
                                          null,
                                          "samirosmanov1988@gmail.com") ;

    @Bean
    public Docket productApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                   .select()
                   .apis(RequestHandlerSelectors.basePackage("az.egov.controller"))
                   .paths(regex("/api.*"))
                   .build()
                   .apiInfo(metaData());


    }

    private ApiInfo metaData() {
          ApiInfo apiInfo = new ApiInfo(
                "Agroculture REST API",
                "Agroculture REST API for front-end developers",
                "1.0",
                null,
                 contact,
                null,
                null);

        return apiInfo;
    }
}
