package com.huang.TibetanLibrary.conf;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
      MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
      mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
      mapperScannerConfigurer.setBasePackage("com.huang.TibetanLibrary.mapper");
//      Properties properties = new Properties();
//      properties.setProperty("mappers", "com.huang.TibetanLibrary.util.MyMapper");
//      properties.setProperty("notEmpty", "false");
//      properties.setProperty("IDENTITY", "MYSQL");
//      mapperScannerConfigurer.setProperties(properties);
      return mapperScannerConfigurer;
  }
}
