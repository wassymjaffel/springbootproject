package tn.esprit.spring.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("tn.esprit.spring"))
		.build().apiInfo(apiInfo());
		}
	private ApiInfo apiInfo () {
		return new ApiInfoBuilder()
		.title("Swagger Configuration for client")
		.description("\"Spring Boot Swagger configuration\"")
		.version("1.1.0").build();
		}
	@Bean
	public WebMvcConfigurer webMvcConfigurer()
	{
	    return new WebMvcConfigurer()
	    {
	        @Override
	        public void addResourceHandlers( ResourceHandlerRegistry registry )
	        {
	            registry.addResourceHandler( "swagger-ui.html" ).addResourceLocations( "classpath:/META-INF/resources/" );
	            registry.addResourceHandler( "/webjars/**" ).addResourceLocations( "classpath:/META-INF/resources/webjars/" );
	        }
	    };
	}

}
