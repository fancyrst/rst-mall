package com.rstang.support.config;

import com.rstang.support.annotation.AuthIgnore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.CharacterEncodingFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties({JacksonProperties.class})
public class WebStarterConfiguration {


    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding("utf-8");
        filter.setForceRequestEncoding(true);
        filter.setForceResponseEncoding(true);
        return filter;
    }

    @Controller
    @AuthIgnore
    @Api(hidden = true)
    public class IndexController implements ApplicationListener<ApplicationReadyEvent> {

        private String applicationName = "application";

        @ApiOperation(value = "首页", hidden = true)
        @RequestMapping("/")
        public String index(Model model) {
            model.addAttribute("applicationName", applicationName);
            return "index";
        }

        @Override
        public void onApplicationEvent(ApplicationReadyEvent event) {
            SpringApplication springApplication = event.getSpringApplication();
            Class<?> mainApplicationClass = springApplication.getMainApplicationClass();
            applicationName = mainApplicationClass.getSimpleName();
        }

    }

}


