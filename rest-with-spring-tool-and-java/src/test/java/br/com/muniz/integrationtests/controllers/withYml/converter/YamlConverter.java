package br.com.muniz.integrationtests.controllers.withYml.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;


public class YamlConverter implements ObjectMapper {

    private com.fasterxml.jackson.databind.ObjectMapper objectMapper;
    protected TypeFactory typeFactory;

    public YamlConverter() {
        objectMapper = new com.fasterxml.jackson.databind.ObjectMapper(new YAMLFactory());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        typeFactory = TypeFactory.defaultInstance();
    }

    @Override
    public Object deserialize(ObjectMapperDeserializationContext context) {
        var data = context.getDataToDeserialize().asString();
        Class type = (Class) context.getType();
        try {
            return objectMapper.readValue(data, typeFactory.constructType(type));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("erro deserialize yaml  content", e);
        }
    }

    @Override
    public Object serialize(ObjectMapperSerializationContext context) {
        try {
            return objectMapper.writeValueAsString(context.getObjectToSerialize());
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("erro deserialize yaml  content", e);
        }
    }
}
