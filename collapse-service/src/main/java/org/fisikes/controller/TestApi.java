package org.fisikes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {


    @GetMapping("test")
    public String test() {
        return "test";
    }
}
