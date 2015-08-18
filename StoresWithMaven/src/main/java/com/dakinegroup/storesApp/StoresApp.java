package com.dakinegroup.storesApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

//derive app class from servlet initializer to be able to host it
// in the container like tomcat
@SpringBootApplication
public class StoresApp extends SpringBootServletInitializer{

	//required for getting loaded in tomcat
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StoresApp.class);
    }

    //only required when we do spring boot.
    public static void main(String[] args) throws Throwable {
        SpringApplication.run(StoresApp.class, args);
    }
 }
