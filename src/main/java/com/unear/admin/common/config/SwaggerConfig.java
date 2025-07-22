package com.unear.admin.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "session";

    @Bean
    public OpenAPI adminApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("UNEAR ADMIN API")
                        .description("유니어 관리자 API 문서입니다.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("관리자1")
                                .email("admin@test.com")
                        )
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"))
                )
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name("JSESSIONID")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.COOKIE) // 세션 인증이면 COOKIE
                        ));
    }
}
