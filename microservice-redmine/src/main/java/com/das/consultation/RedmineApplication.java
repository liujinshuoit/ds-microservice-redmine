package com.das.consultation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 动态数据源配置,需要将自有的配置依赖(DynamicDataSourceConfig),将原有的依赖去除(DataSourceAutoConfiguration)
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class RedmineApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedmineApplication.class, args);
    }

}
