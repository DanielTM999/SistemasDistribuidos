package com.isp_server.isp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectController {
    
    @GetMapping("/")
    public RedirectView  renderSwagger(){
        return new RedirectView("/swagger-ui/index.html");
    }
}
