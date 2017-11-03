package xyz.lomasz.springhelloworld.model;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


public class AirlineTest {

    public static Airline createDumpAirline() {
        Airline dummyAirline = new Airline();

        dummyAirline.setId(99);
        dummyAirline.setName("Dummy Airline");
        dummyAirline.setHub("Dummy Airport");
        dummyAirline.setFleet(Arrays.asList(
                new Airplane("SP-LRA", "B788")
        ));
        dummyAirline.setCrew(Arrays.asList(
                new Crew(99, "John", "Doe", CrewType.PILOT)
        ));

        return dummyAirline;
    }

    @Test
    public void testAirlineObject() throws Exception {
        Airline airline = createDumpAirline();
        assertThat(airline.getId()).isEqualTo(99);
        assertThat(airline.getName()).isEqualTo("Dummy Airline");
        assertThat(airline.getHub()).isEqualTo("Dummy Airport");
        assertThat(airline.getFleet())
                .extracting("tailNumber", "type")
                .contains(tuple("SP-LRA", "B788"));
        assertThat(airline.getCrew())
                .extracting("id", "name", "surname", "crewType")
                .contains(tuple(99, "John", "Doe", CrewType.PILOT));
    }
}