package xyz.lomasz.springhelloworld.model;
import io.swagger.annotations.ApiModelProperty;

public class HelloWorld {

    @ApiModelProperty(notes = "Default: World, but you can set your name as parameter")
    private final String content;

    public HelloWorld(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}