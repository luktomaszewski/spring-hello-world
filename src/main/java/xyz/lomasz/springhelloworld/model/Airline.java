package xyz.lomasz.springhelloworld.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Airline implements Serializable {

    @Id
    private String icao;
    private String name;
    private String hub;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_id", referencedColumnName = "icao")
    private List<Crew> crew;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_id", referencedColumnName = "icao")
    private List<Airplane> fleet;

}
