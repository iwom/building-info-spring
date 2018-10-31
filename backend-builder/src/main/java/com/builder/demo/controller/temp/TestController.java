package com.builder.demo.controller.temp;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping("{text}")
    public String getDemoString(@PathVariable String text) {
        return text;
    }
}
