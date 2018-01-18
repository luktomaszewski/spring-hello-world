package xyz.lomasz.springhelloworld.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class AirlineControllerTest {

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