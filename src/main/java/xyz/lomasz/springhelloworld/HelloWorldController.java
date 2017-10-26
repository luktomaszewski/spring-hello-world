package xyz.lomasz.springhelloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private static final String template = "Hello %s!";

    @RequestMapping("/")
    public HelloWorld sayHi(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new HelloWorld(String.format(template, name));
    }
}
