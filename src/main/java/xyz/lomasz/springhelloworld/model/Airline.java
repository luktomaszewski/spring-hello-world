package xyz.lomasz.springhelloworld.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Airline {

    @ApiModelProperty(notes = "Airline's ID", hidden = true)
    @Id
    @GeneratedValue
    private Long id;
    @ApiModelProperty(notes = "Airline Name", position = 1)
    private String name;
    @ApiModelProperty(notes = "Airline Hub Airport", position = 2)
    private String hub;

    @ApiModelProperty(notes = "Crew's List", position = 3)
    @OneToMany
    private List<Crew> crew;

    @ApiModelProperty(notes = "Airplane's List (Fleet)", position = 4)
    @OneToMany
    private List<Airplane> fleet;

}
