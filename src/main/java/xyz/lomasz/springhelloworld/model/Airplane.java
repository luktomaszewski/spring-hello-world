package xyz.lomasz.springhelloworld.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airplane {

    @ApiModelProperty(notes = "Tail Number")
    private String tailNumber;
    @ApiModelProperty(notes = "Airplane Type", position = 1)
    private String type;

}
