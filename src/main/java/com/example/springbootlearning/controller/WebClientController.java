package com.example.springbootlearning.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/yuvi/services")
public class WebClientController {
    @Autowired
    WebClient.Builder webClient;
    @Value("${url.jsonplaceholder.one.post}")
    private String onePostUrl;

    @ApiOperation(value = "welcome to Api")
    @GetMapping("/welcome2webClient")
    public String welcome2WebClient() {
        return "Welcome to Spring Boot Learning  -  WebClientController";
    }

    @ApiOperation(value = "Getting one post data using - WebClient ")
    @GetMapping("/webclientOnePost")
    public String onePostWithWebClient() {
        var result = webClient.build()
                .get()
                .uri(onePostUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return result;
    }
}
