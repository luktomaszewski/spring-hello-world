package xyz.lomasz.springhelloworld.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.lomasz.springhelloworld.model.HelloWorld;

@RestController
@Api(value = "HelloWorld", description = "How many wood could a woodchuck chuck if a woodchuck could chuck wood?")
public class HelloWorldController {

  private static final String template = "Hello %s!";

  @ApiOperation(value = "Hello!")
  @RequestMapping(value = "helloworld", method = RequestMethod.GET)
  public HelloWorld sayHi(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new HelloWorld(String.format(template, name));
  }
}
