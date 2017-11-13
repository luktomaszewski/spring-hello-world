package xyz.lomasz.springhelloworld.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Crew implements Serializable {

    @ApiModelProperty(notes = "Worker's ID", hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty(notes = "First Name", position = 1)
    private String name;
    @ApiModelProperty(notes = "Last Name", position = 2)
    private String surname;
    @ApiModelProperty(notes = "Crew Type", position = 3)
    private CrewType crewType;

    public Crew(String name, String surname, CrewType crewType) {
        this.name = name;
        this.surname = surname;
        this.crewType = crewType;
    }
}