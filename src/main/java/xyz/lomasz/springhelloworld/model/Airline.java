package xyz.lomasz.springhelloworld.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class Airline {

    @ApiModelProperty(notes = "Airline's ID", hidden = true)
    private int id;
    @ApiModelProperty(notes = "Airline Name", position = 1)
    private String name;
    @ApiModelProperty(notes = "Airline Hub Airport", position = 2)
    private String hub;
    @ApiModelProperty(notes = "Crew's List", position = 3)
    private List<Crew> crew;
    @ApiModelProperty(notes = "Airplane's List (Fleet)", position = 4)
    private List<Airplane> fleet;

}
