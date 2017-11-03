package xyz.lomasz.springhelloworld.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crew {

    @ApiModelProperty(notes = "Worker's ID")
    private int id;
    @ApiModelProperty(notes = "First Name", position = 1)
    private String name;
    @ApiModelProperty(notes = "Last Name", position = 2)
    private String surname;
    @ApiModelProperty(notes = "Crew Type", position = 3)
    private CrewType crewType;

}