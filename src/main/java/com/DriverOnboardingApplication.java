package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableConfigurationProperties
@EntityScan( "com.domain" )
@EnableJpaRepositories( "com.repository" )
@EnableWebMvc
@SpringBootApplication( exclude = {GroovyTemplateAutoConfiguration.class, CassandraAutoConfiguration.class})
public class DriverOnboardingApplication implements CommandLineRunner {
    

    public static void main(String[] args) {
        SpringApplication.run( DriverOnboardingApplication.class, args );
    }
    
    @Override
    public void run(String... args) throws Exception {
    }
    
    
}
