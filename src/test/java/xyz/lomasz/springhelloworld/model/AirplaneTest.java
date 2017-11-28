package xyz.lomasz.springhelloworld.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class AirplaneTest {

  public static Airplane createDummyAirplane() {
    Airplane dummyAirplane = new Airplane();

    dummyAirplane.setTailNumber("SP-LRA");
    dummyAirplane.setType("B788");

    return dummyAirplane;
  }

  @Test
  public void testAirplaneObject() throws Exception {
    Airplane airplane = createDummyAirplane();
    assertThat(airplane.getTailNumber()).isEqualTo("SP-LRA");
    assertThat(airplane.getType()).isEqualTo("B788");
  }
}
