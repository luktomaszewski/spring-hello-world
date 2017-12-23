package xyz.lomasz.springhelloworld.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

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

  private String createAirline(String path, Object bodyPayload) {
    return given()
        .spec(spec)
        .body(bodyPayload)
        .when()
        .post(path)
        .then()
        .statusCode(201)
        .extract()
        .header("location");
  }

  private <T> T getAirline(String locationHeader, Class<T> responseClass) {
    return given()
        .spec(spec)
        .when()
        .get(locationHeader)
        .then()
        .statusCode(200)
        .extract().as(responseClass);
  }

  private void deleteAirline(String locationHeader) {
    given()
        .spec(spec)
        .when()
        .delete(locationHeader)
        .then()
        .statusCode(200);
  }

  private void assertEqualAirline(Airline newAirline, Airline retrievedAirline) {
    assertThat(newAirline).
        isEqualToIgnoringGivenFields(retrievedAirline, "id", "crew");

    assertThat(newAirline.getCrew())
        .usingElementComparatorIgnoringFields("id")
        .isEqualTo(retrievedAirline.getCrew());
  }

  @Test
  public void testCreateAirline() throws Exception {
    //STEP 1: Adding new airline -> expected HTTP Status: 201 - CREATED
    Airline newAirline = createDummyAirline("Dummy");
    String newAirlineLocation = createAirline(PATH, newAirline);

    //STEP 2: Getting airline by ID -> expected HTTP Status: 200 - OK
    Airline retrievedAirline = getAirline(newAirlineLocation, Airline.class);

    //STEP 3: Comparing new and retrieved airlines
    assertEqualAirline(newAirline, retrievedAirline);

    //STEP 5: Deleting airline -> expected HTTP Status: 200 - OK
    deleteAirline(newAirlineLocation);
  }

  @Test
  public void testCreateDuplicatedAirline() throws Exception {
    //STEP 1: Adding new airline -> expected HTTP Status: 201 - CREATED
    Airline newAirline = createDummyAirline("Dummy");
    String newAirlineLocation = createAirline(PATH, newAirline);

    //STEP 2: Getting airline by ID -> expected HTTP Status: 200 - OK
    Airline retrievedAirline = getAirline(newAirlineLocation, Airline.class);

    //STEP 3: Comparing new and retrieved airlines
    assertEqualAirline(newAirline, retrievedAirline);

    //STEP 4: Adding again the same object (duplicate) -> expected HTTP Status: 409 - CONFLICT
    given()
        .spec(spec)
        .body(newAirline)
        .when()
        .post(PATH)
        .then()
        .statusCode(409);

    //STEP 5: Deleting airline -> expected HTTP Status: 200 - OK
    deleteAirline(newAirlineLocation);
  }

  @Test
  public void testGetAllAirlines() throws Exception {
    //STEP 1: Adding new airlines -> expected HTTP Status: 201 - CREATED
    Airline firstAirline = createDummyAirline("Fist");
    String firstAirlineLocation = createAirline(PATH, firstAirline);

    Airline secondAirline = createDummyAirline("Second");
    String secondAirlineLocation = createAirline(PATH, secondAirline);

    //STEP 2: Getting all airlines - expected HTTP Status: 200 - OK
    Airline[] airlineList = given()
        .spec(spec)
        .when()
        .get(PATH)
        .then()
        .statusCode(200)
        .extract().as(Airline[].class);

    //STEP 3: Checking size of list - expected: 2
    assertThat(airlineList).hasSize(2);

    //STEP 4: Comparing new airlines with retrieved airlines
    //STEP 4.1: Comparing First Airline: new with retrieved
    assertEqualAirline(
        Arrays.stream(airlineList)
            .filter(airline -> airline.getName().equals(firstAirline.getName()))
            .findAny()
            .orElse(null),
        firstAirline);

    //STEP 4.2: Comparing Second Airline: new with retrieved
    assertEqualAirline(
        Arrays.stream(airlineList)
            .filter(airline -> airline.getName().equals(secondAirline.getName()))
            .findAny()
            .orElse(null),
        secondAirline);

    // STEP 5: Deleting airlines -> expected HTTP Status: 200 - OK
    deleteAirline(firstAirlineLocation);
    deleteAirline(secondAirlineLocation);
  }

  @Test
  public void testGetAirlineByID() throws Exception {
    // STEP 1: Add new airlines -> expected HTTP Status: 201 - CREATED
    Airline firstAirline = createDummyAirline("First");
    String firstAirlineLocation = createAirline(PATH, firstAirline);

    Airline secondAirline = createDummyAirline("Second");
    String secondAirlineLocation = createAirline(PATH, secondAirline);

    //STEP 2: Getting airlines by ID -> expected HTTP Status: 200 - OK
    Airline firstRetrievedAirline = getAirline(firstAirlineLocation, Airline.class);
    Airline secondRetrievedAirline = getAirline(secondAirlineLocation, Airline.class);

    //STEP 3: Comparing new and retrieved airlines
    assertEqualAirline(firstAirline, firstRetrievedAirline);
    assertEqualAirline(secondAirline, secondRetrievedAirline);

    //STEP 4: Deleting airlines -> expected HTTP Status: 200 - OK
    deleteAirline(firstAirlineLocation);
    deleteAirline(secondAirlineLocation);
  }

  @Test
  public void testRemoveAirline() throws Exception {
    //STEP 1: Adding new airline -> expected HTTP Status: 201 - CREATED
    Airline newAirline = createDummyAirline("Dummy");
    String newAirlineLocation = createAirline(PATH, newAirline);

    //STEP 2: Getting airline by ID (check if airline is exist) -> expected HTTP Status: 200 - OK
    getAirline(newAirlineLocation, Airline.class);

    // STEP 3: Removing airline -> Expected HTTP Status: 200 - OK
    deleteAirline(newAirlineLocation);

    //STEP 4: Getting airline by ID (should not exist) -> expected HTTP Status: 404 - Not Found
    given()
        .spec(spec)
        .when()
        .get(newAirlineLocation)
        .then()
        .statusCode(404);
  }
}