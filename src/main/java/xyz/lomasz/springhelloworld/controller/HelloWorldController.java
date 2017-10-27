package xyz.lomasz.springhelloworld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.lomasz.springhelloworld.model.HelloWorld;

@RestController
public class HelloWorldController {

    private static final String template = "Hello %s!";

    @RequestMapping(value = "helloworld", method = RequestMethod.GET)
    public HelloWorld sayHi(@RequestParam(value = "name", defaultValue = "World") String name ) {
        return new HelloWorld(String.format(template, name));
    }
}
