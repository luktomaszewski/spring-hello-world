package xyz.lomasz.springhelloworld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.lomasz.springhelloworld.model.Timestamp;


@RestController
public class TimestampController {

    @RequestMapping(value = "getDateTime", method = RequestMethod.GET)
    Timestamp getDateTime(){
        return new Timestamp();
    }
}
