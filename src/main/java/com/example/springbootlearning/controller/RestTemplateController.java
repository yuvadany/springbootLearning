package com.example.springbootlearning.controller;

import com.example.springbootlearning.model.Book;
import com.example.springbootlearning.model.Post;
import com.example.springbootlearning.model.SubscriptionModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/yuvi/services")
public class RestTemplateController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Book book;
    private static final String ALL_POST_URL = "http://jsonplaceholder.typicode.com/posts";
    @Value("${url.jsonplaceholder.one.post}")
    private String onePostUrl;
    @Value("${local.post.api}")
    private String localPostApi;
    @Value("${local.get.with.requestparam.api}")
    private String localgetWithRequest;
    @Value("${local.get.api.xml.response}")
    private String localGetXml;
    //http://localhost:8989/yuvi/services/apis/sampleRequestBody
    @Value("${local.get.api.post.requestbody}")
    private String localPostRequestBody;

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

    @ApiOperation(value = " Api To call - local GET api which has Query String")
    @GetMapping("/getForEntityWithRequestParam/{book_id}/{author_name}")
    public ResponseEntity<Book> getForEntityWithRequestParam(@PathVariable int book_id, @PathVariable String author_name) {
        var result = restTemplate.getForEntity(localgetWithRequest, Book.class,book_id,author_name);
        return new ResponseEntity<>(result.getBody(), HttpStatus.OK);
    }


    @ApiOperation(value = "using exchange - get one post from http://jsonplaceholder.typicode.com/posts/1")
    @GetMapping("/postExchange/1")
    public ResponseEntity<Post> exchangeRestTemplate() throws URISyntaxException {
        RequestEntity<Post> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(onePostUrl));
        var result = restTemplate.exchange(requestEntity, Post.class);
        return new ResponseEntity<Post>(result.getBody(), HttpStatus.OK);
    }

//http://localhost:8989/yuvi/services/apis/postSubscriptionApi
    @ApiOperation(value = "exchange - local post API - Call")
    @GetMapping("/localPostApiCall")
    public ResponseEntity<SubscriptionModel> localPostApiCall() throws URISyntaxException {
        RequestEntity<SubscriptionModel> requestEntity = new RequestEntity<>(HttpMethod.POST, new URI(localPostApi));
        var result = restTemplate.exchange(requestEntity, SubscriptionModel.class);
        return new ResponseEntity<SubscriptionModel>(result.getBody(), HttpStatus.OK);
    }

    //http://localhost:8989/yuvi/services/apis/postSubscriptionApi
    @ApiOperation(value = "postForEntity - local post API - Call")
    @GetMapping("/postForEntityLocalAPI")
    public ResponseEntity<SubscriptionModel> postForEntityLocalAPI() throws URISyntaxException {
        RequestEntity<SubscriptionModel> requestEntity = new RequestEntity<>(HttpMethod.POST, new URI(localPostApi));
        var result = restTemplate.postForEntity( localPostApi , "",SubscriptionModel.class);
        return new ResponseEntity<SubscriptionModel>(result.getBody(), HttpStatus.OK);
    }

    //http://localhost:8989/yuvi/services/apis/get/xmlResponse
    @ApiOperation(value = "exchange - local post API - xml Response")
    @GetMapping(value = "/localGetXMLapi",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Book> localGetXMLapi() throws URISyntaxException {
        RequestEntity<SubscriptionModel> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(localGetXml+"?author_name=yuvaraj_palanisamy"));
        var result = restTemplate.exchange(requestEntity, Book.class);
        return new ResponseEntity<Book>(result.getBody(), HttpStatus.OK);
    }


    @ApiOperation(value = "With Request Body - local post API - ")
    @PostMapping(value = "/localPostRequestBody/{bookId}/{bookName}",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> localPostRequestBody(@PathVariable int bookId,@PathVariable String bookName) throws URISyntaxException {
        book.setBook_id(bookId);
        book.setBook_name(bookName);
        book.setAuthor("YUVARAJ PALANISAMY");
        var result = restTemplate.postForEntity(new URI(localPostRequestBody), book, String.class);
        return new ResponseEntity<String>(result.getBody(), HttpStatus.OK);
    }

}