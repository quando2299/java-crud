package com.example.crud;

import java.util.List;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
public class CrudApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudApplication.class, args);
  }

  @Configuration
  @EnableWebMvc
  public static class WebappConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
      Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
      builder.serializerByType(ObjectId.class, new ToStringSerializer());
      MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
      converters.add(converter);
    }
  }
}
