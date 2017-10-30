package xyz.lomasz.springhelloworld.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Airplane {

    @ApiModelProperty(notes = "Tail Number")
    private String tailNumber;
    @ApiModelProperty(notes = "Airplane Type", position = 1)
    private String type;

}
