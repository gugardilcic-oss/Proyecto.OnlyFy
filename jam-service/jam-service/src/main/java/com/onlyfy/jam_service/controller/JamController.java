package com.onlyfy.jam_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JamController {

    @GetMapping("/jam")
    public String getJam() {
        return "This is the jam service!";
    }

}


