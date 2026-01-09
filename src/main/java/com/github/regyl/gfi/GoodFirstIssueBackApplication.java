package com.github.regyl.gfi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "com.github.regyl.gfi.configuration")
public class GoodFirstIssueBackApplication {

    static void main(String[] args) {
        SpringApplication.run(GoodFirstIssueBackApplication.class, args);
    }

}
