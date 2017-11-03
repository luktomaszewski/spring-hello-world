package xyz.lomasz.springhelloworld.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CrewTest {

    public static Crew createDumpCrew() {
        Crew dumpCrew = new Crew();

        dumpCrew.setId(99);
        dumpCrew.setName("John");
        dumpCrew.setSurname("Doe");
        dumpCrew.setCrewType(CrewType.PILOT);

        return dumpCrew;
    }

    @Test
    public void testCrewObject() throws Exception {
        Crew crew = createDumpCrew();
        assertThat(crew.getId()).isEqualTo(99);
        assertThat(crew.getName()).isEqualTo("John");
        assertThat(crew.getSurname()).isEqualTo("Doe");
        assertThat(crew.getCrewType()).isEqualTo(CrewType.PILOT);
    }
}