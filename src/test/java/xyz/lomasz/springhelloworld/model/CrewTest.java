package xyz.lomasz.springhelloworld.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CrewTest {

    private static Crew createDummyCrew() {
        Crew dummyCrew = new Crew();

        dummyCrew.setId(99L);
        dummyCrew.setName("John");
        dummyCrew.setSurname("Doe");
        dummyCrew.setCrewType(CrewType.PILOT);

        return dummyCrew;
    }

    @Test
    public void testCrewObject() throws Exception {
        Crew crew = createDummyCrew();
        assertThat(crew.getId()).isEqualTo(99);
        assertThat(crew.getName()).isEqualTo("John");
        assertThat(crew.getSurname()).isEqualTo("Doe");
        assertThat(crew.getCrewType()).isEqualTo(CrewType.PILOT);
    }
}