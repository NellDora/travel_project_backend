package com.nelldora.travel.vacationland.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/informations")
public class InfoController {

    @GetMapping("")
    public String list(){
        return null;
    }

    @GetMapping("/test")
    public String test(){
        return "testOk";
    }

}
