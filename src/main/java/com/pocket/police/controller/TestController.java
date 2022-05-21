package com.pocket.police.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class TestController {
    @GetMapping("/test")
    @ResponseBody
    public String testReturn() {
        return "success controller!";
    }
}
