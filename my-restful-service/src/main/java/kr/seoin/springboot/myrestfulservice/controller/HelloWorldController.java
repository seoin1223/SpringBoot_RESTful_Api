package kr.seoin.springboot.myrestfulservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // GET
    // url - /hello-world
    // RequestMapping(method - RequestMethod.GET, path"/hello-world)
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
}
