package xyz.lomasz.springhelloworld.model;

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
    @Id
    private String tailNumber;
    private String type;
}
