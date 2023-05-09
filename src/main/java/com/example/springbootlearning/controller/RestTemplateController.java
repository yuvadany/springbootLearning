package com.example.springbootlearning.controller;

import com.example.springbootlearning.model.Post;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/yuvi/services")
public class RestTemplateController {
    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "welcome Api")
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Spring Boot Learning";
    }

    @ApiOperation(value = "to get All posts from http://jsonplaceholder.typicode.com/posts")
    @GetMapping("/all_posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        var result = restTemplate.getForObject("http://jsonplaceholder.typicode.com/posts", List.class);
        return new ResponseEntity<List<Post>>((List<Post>) result, HttpStatus.OK);
    }

    @ApiOperation(value = "to get one post from http://jsonplaceholder.typicode.com/posts/1")
    @GetMapping("/post/1")
    public ResponseEntity<String> getOnePost() {
        var result = restTemplate.getForObject("http://jsonplaceholder.typicode.com/posts/1", Post.class);
        return new ResponseEntity<String>(result.getBody(), HttpStatus.OK);
    }


    @ApiOperation(value = "to get one post from http://jsonplaceholder.typicode.com/posts/1")
    @GetMapping("/postEntity/1")
    public ResponseEntity<Post> getForEntity() {
        var result = restTemplate.getForEntity("http://jsonplaceholder.typicode.com/posts/1", Post.class);
        return new ResponseEntity<Post>(result.getBody(), HttpStatus.OK);
    }
}