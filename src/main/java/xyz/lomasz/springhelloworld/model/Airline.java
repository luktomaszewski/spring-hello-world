package xyz.lomasz.springhelloworld.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Airline implements Serializable {

    @ApiModelProperty(notes = "Airline's ID", hidden = true)
    @Id
    @GeneratedValue
    private Long id;
    @ApiModelProperty(notes = "Airline ICAO", position = 1)
    private String icao;

    @ApiModelProperty(notes = "Airline Name", position = 2)
    private String name;
    @ApiModelProperty(notes = "Airline Hub Airport", position = 3)
    private String hub;

    @ApiModelProperty(notes = "Crew's List", position = 4)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private List<Crew> crew;

    @ApiModelProperty(notes = "Airplane's List (Fleet)", position = 5)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private List<Airplane> fleet;

}
