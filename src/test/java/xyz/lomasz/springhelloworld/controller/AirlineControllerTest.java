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
  public void createAirlineNewShouldReturnCreatedStatus() throws Exception {
    // TODO : Unit Test: createAirline(airline) -> HttpStatus: 201 CREATED
    // given


    // when


    // then


}

  @Test
  public void createAirlineDuplicatedShouldReturnConflictStatus() throws Exception {
    // TODO : Unit Test: createAirline(airline) -> HttpStatus: 409 CONFLICT
    // given


    // when


    // then


  }

  @Test
  public void getAirlineExistedShouldReturnAirline() throws Exception {
    // TODO : Unit Test: getAirline(icao) -> Airline
    // given


    // when


    // then


  }

  @Test
  public void getAirlineNotExistedShouldReturnNotFoundStatus() throws Exception {
    // TODO : Unit Test: getAirline(icao) -> HttpStatus: 404 NOT FOUND
    // given


    // when


    // then


  }

  @Test
  public void deleteAirlineExistedShouldReturnOkStatus() throws Exception {
    // TODO : Unit Test: deleteAirline(icao) -> HttpStatus: 200 OK
    // given


    // when


    // then


  }

  @Test
  public void deleteAirlineNotExistedShouldReturnNotFoundStatus() throws Exception {
    // TODO : Unit Test: deleteAirline(icao) -> HttpStatus: 404 NOT FOUND
    // given


    // when


    // then


  }

  @Test
  public void listAllAirlinesExistedShouldReturnAirlineList() throws Exception {
    // TODO : Unit Test: listAllAirlines() -> List<Airline>
    // given


    // when


    // then


  }

  @Test
  public void listAllAirlinesNotExistedShouldReturnNotFoundStatus() throws Exception {
    // TODO : Unit Test: listAllAirlines() -> HttpStatus: 404 NOT FOUND
    // given


    // when


    // then


  }

}