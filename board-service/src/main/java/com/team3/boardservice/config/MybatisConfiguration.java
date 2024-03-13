package com.team3.boardservice.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.team3.boardservice.recruitment.repository", annotationClass = Mapper.class)
public class MybatisConfiguration {
}