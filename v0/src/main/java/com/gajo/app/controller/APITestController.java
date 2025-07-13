package com.gajo.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APITestController {

    @GetMapping("/api/apitest")
    public String hello() {
        return "Hola desde GAJO";
    }
}
