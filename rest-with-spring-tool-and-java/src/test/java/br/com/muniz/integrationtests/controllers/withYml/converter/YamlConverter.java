package br.com.muniz.integrationtests.controllers.withYml.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

@Configuration
public class YamlJackson2HttpMessgeConverter extends AbstractJackson2HttpMessageConverter {

    private YamlJackson2HttpMessgeConverter() {
        super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL), MediaType.parseMediaType("application/yaml"));
    }

    static YamlJackson2HttpMessgeConverter createYamlJackson2HttpMessgeConverter() {
        return new YamlJackson2HttpMessgeConverter();
    }
}
