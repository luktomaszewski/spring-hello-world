package xyz.lomasz.springhelloworld.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Airplane implements Serializable {

    @ApiModelProperty(notes = "Tail Number")
    @Id
    private String tailNumber;
    @ApiModelProperty(notes = "Airplane Type", position = 1)
    private String type;
}
