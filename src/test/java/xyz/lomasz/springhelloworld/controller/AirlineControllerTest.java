package xyz.lomasz.springhelloworld.controller;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.lomasz.springhelloworld.model.Airline;
import xyz.lomasz.springhelloworld.model.Airplane;
import xyz.lomasz.springhelloworld.model.Crew;
import xyz.lomasz.springhelloworld.model.CrewType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class AirlineControllerTest {

  private static final String PATH = "airline";
  private static RequestSpecification spec;

  @LocalServerPort
  int randomServerPort;

  @Before
  public void initSpec() {
    spec = new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setPort(randomServerPort)
        .addFilter(new ResponseLoggingFilter())
        .addFilter(new RequestLoggingFilter())
        .build();
  }

  private Airline createDummyAirline(String name) {
    Airline dummyAirline = new Airline();

    dummyAirline.setIcao(name);
    dummyAirline.setName(name + " Airline");
    dummyAirline.setHub(name + " Airport");
    dummyAirline.setFleet(Arrays.asList(new Airplane(name, "B788")));
    dummyAirline.setCrew(Arrays.asList(new Crew(name, "Pilot", CrewType.PILOT)));

    return dummyAirline;
  }

  @Test
  public void createAirline_newObject_shouldReturnCreatedStatus() throws Exception {
    // given


    // when


    // then


}

  @Test
  public void createAirline_duplicatedObject_shouldReturnConflictStatus() throws Exception {
    // given


    // when


    // then


  }

  @Test
  public void getAirline_existed_shouldReturnAirline() throws Exception {
    // given


    // when


    // then


  }

  @Test
  public void getAirline_notExisted_shouldReturnNotFoundStatus() throws Exception {
    // given


    // when


    // then


  }

  @Test
  public void deleteAirline_existed_shouldReturnOkStatus() throws Exception {
    // given


    // when


    // then


  }

  @Test
  public void deleteAirline_notExisted_shouldReturnNotFoundStatus() throws Exception {
    // given


    // when


    // then


  }

  @Test
  public void listAllAirlines_exists_shouldReturnAirlineList() throws Exception {
    // given


    // when


    // then


  }

  @Test
  public void listAllAirlines_lackOfObjects_shouldReturnNotFoundStatus() throws Exception {
    // given


    // when


    // then


  }

}