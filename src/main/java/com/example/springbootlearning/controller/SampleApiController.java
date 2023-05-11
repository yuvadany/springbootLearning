package com.example.springbootlearning.controller;

import com.example.springbootlearning.model.Book;
import com.example.springbootlearning.model.SubscriptionModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/yuvi/services")
public class SampleApiController {
    @Autowired
    private SubscriptionModel subscriptionModel;
    @Autowired
    private Book book;

    @ApiOperation("local Get API")
    @GetMapping("/apis/getWelcomeApi")
    public String getWelcomeApi() {
        return "--- getWelcomeApi from SampleApiController.java--- ";
    }

    @ApiOperation("local POST API without inputs")
    @PostMapping("/apis/postWelcomeApi")
    public String postWelcomeApi() {
        return "--- post API call from SampleApiController.java--- ";
    }

    @ApiOperation("local POST API without inputs")
    @PostMapping("/apis/postSubscriptionApi")
    public SubscriptionModel postSubscriptionApi() {
        subscriptionModel.setSubscriptionId("123456");
        subscriptionModel.setBrand("o2");
        subscriptionModel.setCustomerName("Yuvaraj Palanisamy");
        subscriptionModel.setMsisdn("8989787876");
        return subscriptionModel;
    }

    @ApiOperation("local Get API for XML Response with Request Param - Query String")
    @GetMapping(value= "/apis/xmlResponse",produces= MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Book> getXmlResponse(@RequestParam String author_name) {
        book.setBook_id(1);
        book.setBook_name("Java 8 Advanced");
        book.setAuthor(author_name);
        ResponseEntity<Book> response = new ResponseEntity<>(book, HttpStatus.OK);
        return response;
    }

    @ApiOperation("local POST API - with Request Body - XML Response")
    @PostMapping(value= "/apis/xmlResponse",produces= MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Book> requestBodyXmlResponse(@RequestBody Book book_input) {
        ResponseEntity<Book> response = new ResponseEntity<>(book_input, HttpStatus.OK);
        return response;
    }

    @ApiOperation("local POST API - with Request Body")
    @PostMapping(value= "/apis/sampleRequestBody")
    public ResponseEntity<String> getXmlResponseWithRequestBody(@RequestBody Book book_input) {
        ResponseEntity<String> response = new ResponseEntity<>(book_input.getBook_name(), HttpStatus.OK);
        return response;
    }

    @ApiOperation(" Json Response with Path Variable - Local GET API")
    @GetMapping(value= "/apis/jsonResponse/{book_id}",produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getJsonResponse(@PathVariable int book_id) {
        book.setBook_id(book_id);
        book.setBook_name("Spring Boot 2+");
        book.setAuthor("Spring Boot - Author");
        ResponseEntity<Book> response = new ResponseEntity<>(book, HttpStatus.OK);
        return response;
    }

}
