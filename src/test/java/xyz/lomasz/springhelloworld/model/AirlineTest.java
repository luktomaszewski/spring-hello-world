package xyz.lomasz.springhelloworld.model;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static xyz.lomasz.springhelloworld.model.CrewTest.createDumpCrew;


public class AirlineTest {

    public static Airline createDumpAirline() {
        Airline dumpAirline = new Airline();
        dumpAirline.setId(99);
        dumpAirline.setName("Dump Airlines");
        dumpAirline.setHub("Dump Airport");
        dumpAirline.setCrew(Arrays.asList(createDumpCrew()));
        dumpAirline.setFleet(Arrays.asList(AirplaneTest.createDumpAirplane()));

        return dumpAirline;
    }

    @Test
    public void testAirlineObject() throws Exception {
        Airline airline = createDumpAirline();
        assertThat(airline.getId()).isEqualTo(99);
        assertThat(airline.getName()).isEqualTo("Dump Airlines");
        assertThat(airline.getHub()).isEqualTo("Dump Airport");
        assertThat(airline.getCrew())
                .extracting(crew -> this);
        assertThat(airline.getFleet())
                .extracting("tailNumber", "type")
                .contains(tuple("SP-LRA", "B788"));
        assertThat(airline.getCrew())
                .extracting("id", "name", "surname", "crewType")
                .contains(tuple((long) 99, "John", "Doe", CrewType.PILOT));
    }
}