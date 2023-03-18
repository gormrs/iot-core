package com.my.api.spring;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/throwException")
    public ResponseEntity<String> testEndpoint() throws Exception {
        throw new Exception("An error occurred");
    }
}