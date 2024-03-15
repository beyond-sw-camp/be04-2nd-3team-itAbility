package com.team3.boardservice.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.team3.boardservice.recruitment.repository", annotationClass = Mapper.class)
public class MybatisConfiguration {

    @Bean
    public Hibernate5Module hibernate5Module() {
        Hibernate5Module module = new Hibernate5Module();
        // 필요한 경우 추가 설정을 여기에 추가할 수 있습니다.
        return module;
    }
}