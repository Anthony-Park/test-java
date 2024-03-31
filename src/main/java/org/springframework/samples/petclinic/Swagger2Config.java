package org.springframework.samples.petclinic;

/*
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
	info = @Info(title = "Couple App",
		description = "couple app api명세",
		version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi chatOpenApi() {
		String[] paths = {"/v1/**"};

		return GroupedOpenApi.builder()
			.group("COUPLE API v1")
			.pathsToMatch(paths)
			.build();
	}
}
*/
/*
//@EnableSwagger2
//@Configuration
public class Swagger2Config {
	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
			.group("v1-definition")
			.pathsToMatch("/api/**")
			.build();
	}
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
			.info(title("Bstagram API")
			.description("BMW 프로젝트 API 명세서입니다.")
			.version("v0.0.1");
	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.cadi.team3"))
			.paths(PathSelectors.ant("/api/**"))
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("cadi project")
			.version("1.0")
			.description("movie & sns project apis")
			.build();
	}
}
*/
