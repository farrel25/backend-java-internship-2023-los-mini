package com.los.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/example")
public class ExampleController {

    @GetMapping
    public String exampleMethod() {
        return "Hello World Secured";
    }
}
