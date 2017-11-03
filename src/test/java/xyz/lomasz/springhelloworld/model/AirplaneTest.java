package xyz.lomasz.springhelloworld.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AirplaneTest {

    public static Airplane createDumpAirplane() {
        Airplane dumpAirplane = new Airplane();

        dumpAirplane.setTailNumber("SP-LRA");
        dumpAirplane.setType("B788");

        return dumpAirplane;
    }

    @Test
    public void testAirplaneObject() throws Exception {
        Airplane airplane = createDumpAirplane();
        assertThat(airplane.getTailNumber()).isEqualTo("SP-LRA");
        assertThat(airplane.getType()).isEqualTo("B788");
    }
}