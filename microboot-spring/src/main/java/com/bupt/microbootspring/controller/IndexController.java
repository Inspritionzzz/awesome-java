package com.bupt.microbootspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @RequestMapping("/")
    String index() {
        return "just a test";
    }

    @RequestMapping("/index")
    String indexDemo() {
        return "just a test";
    }
}
