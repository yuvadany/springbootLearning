package com.example.springbootlearning.controller;

import com.example.springbootlearning.model.Post;
import com.example.springbootlearning.model.SubscriptionModel;
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
    @Value("${local.post.api}")
    private String localPostApi;

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

    @ApiOperation(value = "Getting one post data using - WebClient ")
    @GetMapping("/webclientEntiryOnePost")
    public String onePostWithWebClientEntity() {
        var result = webClient.build()
                .get()
                .uri(onePostUrl)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
        return result.getTitle();
    }

    @ApiOperation(value = "Getting one post data using - WebClient ")
    @GetMapping("/webclientWithLocalPostApi")
    public SubscriptionModel webclientWithLocalPostApi() {
        var result = webClient.build()
                .post()
                .uri(localPostApi)
                .retrieve()
                .bodyToMono(SubscriptionModel.class)
                .block();
        return result;
    }
}
