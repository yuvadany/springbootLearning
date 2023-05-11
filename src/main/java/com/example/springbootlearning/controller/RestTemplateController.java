package com.example.springbootlearning.controller;

import com.example.springbootlearning.model.Post;
import com.example.springbootlearning.model.SubscriptionModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/yuvi/services")
public class RestTemplateController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String ALL_POST_URL = "http://jsonplaceholder.typicode.com/posts";
    @Value("${url.jsonplaceholder.one.post}")
    private String onePostUrl;
    @Value("${local.post.api}")
    private String localPostApi;


    @ApiOperation(value = "welcome Api")
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Spring Boot Learning";
    }

    @ApiOperation(value = "to get All posts from http://jsonplaceholder.typicode.com/posts")
    @GetMapping("/all_posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        var result = restTemplate.getForObject(ALL_POST_URL, List.class);
        return new ResponseEntity<List<Post>>((List<Post>) result, HttpStatus.OK);
    }

    @ApiOperation(value = "to get one post from http://jsonplaceholder.typicode.com/posts/1")
    @GetMapping("/post/1")
    public ResponseEntity<String> getOnePost() {
        var result = restTemplate.getForObject(onePostUrl, Post.class);
        if (result != null) {
            return new ResponseEntity<String>(result.getBody(), HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }


    @ApiOperation(value = "to get one post from http://jsonplaceholder.typicode.com/posts/1")
    @GetMapping("/postEntity/1")
    public ResponseEntity<Post> getForEntity() {
        var result = restTemplate.getForEntity(onePostUrl, Post.class);
        return new ResponseEntity<>(result.getBody(), HttpStatus.OK);
    }

    @ApiOperation(value = "postForEntity - local post API - Call")
    @GetMapping("/postForEntityLocalAPI")
    public ResponseEntity<SubscriptionModel> postForEntityLocalAPI() throws URISyntaxException {
        RequestEntity<SubscriptionModel> requestEntity = new RequestEntity<>(HttpMethod.POST, new URI(localPostApi));
        var result = restTemplate.postForEntity( localPostApi , "",SubscriptionModel.class);
        return new ResponseEntity<SubscriptionModel>(result.getBody(), HttpStatus.OK);
    }

    @ApiOperation(value = "using exchange - get one post from http://jsonplaceholder.typicode.com/posts/1")
    @GetMapping("/postExchange/1")
    public ResponseEntity<Post> exchangeRestTemplate() throws URISyntaxException {
        RequestEntity<Post> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(onePostUrl));
        var result = restTemplate.exchange(requestEntity, Post.class);
        return new ResponseEntity<Post>(result.getBody(), HttpStatus.OK);
    }


    @ApiOperation(value = "exchange - local post API - Call")
    @GetMapping("/localPostApiCall")
    public ResponseEntity<SubscriptionModel> localPostApiCall() throws URISyntaxException {
        RequestEntity<SubscriptionModel> requestEntity = new RequestEntity<>(HttpMethod.POST, new URI(localPostApi));
        var result = restTemplate.exchange(requestEntity, SubscriptionModel.class);
        return new ResponseEntity<SubscriptionModel>(result.getBody(), HttpStatus.OK);
    }
}