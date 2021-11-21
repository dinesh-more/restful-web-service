package com.example.restws.controllers;

import com.example.restws.beans.ResponseMessage;
import com.example.restws.models.TestBean;
import com.example.restws.repositories.UserRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello-world")
    public ResponseMessage helloWorld() {
        return new ResponseMessage("Hello World");
    }

    /*@GetMapping("/hello-world-internationalization")
    public String helloWorldIn(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        System.out.println("Locale: "+locale);
        return messageSource.getMessage("initial.message", null, "default", locale);
    }*/

    @GetMapping("/hello-world-internationalization")
    public String helloWorldIn() {
        return messageSource.getMessage("initial.message", null, "default", LocaleContextHolder.getLocale());
    }

    //Dynamic Filtering
    @GetMapping(value = "/dynamic-filter")
    public MappingJacksonValue retrieveListOfTestUser() {
        List<TestBean> list = Arrays.asList(
                new TestBean("user1", "12345", 1234567895, 24),
                new TestBean("user2", "123456", 1244562895, 22)
        );
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("name","mobile_number");
        FilterProvider filters = new
                SimpleFilterProvider().addFilter("testBeanFilter", filter);
        MappingJacksonValue mapping = new
                MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;
    }

}
