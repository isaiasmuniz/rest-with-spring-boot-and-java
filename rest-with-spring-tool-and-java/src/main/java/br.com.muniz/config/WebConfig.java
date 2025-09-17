package br.com.muniz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.originPatterns}")
    private String corsOriginPatterns = "";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedCoraOrigins = corsOriginPatterns.split(",");
        registry.addMapping("/**")
                .allowedOrigins(allowedCoraOrigins)
                .allowedMethods("*").allowCredentials(true);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        //query param
//        configurer.favorParameter(true)
//                .parameterName("mediaType")
//                .ignoreAcceptHeader(true)
//                .useRegisteredExtensionsOnly(false)
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("Json", MediaType.APPLICATION_JSON)
//                .mediaType("XML", MediaType.APPLICATION_XML);
//    }

    //header param
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("Json", MediaType.APPLICATION_JSON)
                .mediaType("yaml", MediaType.APPLICATION_YAML)
                .mediaType("XML", MediaType.APPLICATION_XML);
    }
}
