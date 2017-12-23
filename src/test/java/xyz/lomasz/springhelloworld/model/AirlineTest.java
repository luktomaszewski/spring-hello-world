package xyz.lomasz.springhelloworld.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.Arrays;
import org.junit.Test;


public class AirlineTest {

  private static Airline createDummyAirline() {
    Airline dummyAirline = new Airline();

    dummyAirline.setId(99L);
    dummyAirline.setIcao("LOM");
    dummyAirline.setName("Dummy Airline");
    dummyAirline.setHub("Dummy Airport");
    dummyAirline.setFleet(Arrays.asList(
        new Airplane("SP-LRA", "B788")
    ));
    dummyAirline.setCrew(Arrays.asList(
        new Crew(99L, "John", "Doe", CrewType.PILOT)
    ));

    return dummyAirline;
  }

  @Test
  public void testAirlineObject() throws Exception {
    Airline airline = createDummyAirline();
    assertThat(airline.getId()).isEqualTo(99);
    assertThat(airline.getIcao()).isEqualTo("LOM");
    assertThat(airline.getName()).isEqualTo("Dummy Airline");
    assertThat(airline.getHub()).isEqualTo("Dummy Airport");
    assertThat(airline.getFleet())
        .extracting("tailNumber", "type")
        .contains(tuple("SP-LRA", "B788"));
    assertThat(airline.getCrew())
        .extracting("id", "name", "surname", "crewType")
        .contains(tuple(99L, "John", "Doe", CrewType.PILOT));
  }
}