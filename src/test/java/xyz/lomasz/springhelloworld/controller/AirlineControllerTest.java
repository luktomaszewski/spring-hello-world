package xyz.lomasz.springhelloworld.controller;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.lomasz.springhelloworld.model.Airline;
import xyz.lomasz.springhelloworld.model.Airplane;
import xyz.lomasz.springhelloworld.model.Crew;
import xyz.lomasz.springhelloworld.model.CrewType;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AirlineControllerTest {

    public static final String PATH = "airline";
    private static RequestSpecification spec;

    @BeforeClass
    public static void initSpec() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setPort(666)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    Airline createDummyAirline(String name) {
        Airline dummyAirline = new Airline();

        dummyAirline.setName(name + " Airline");
        dummyAirline.setHub(name + " Airport");
        dummyAirline.setFleet(Arrays.asList(new Airplane(name, "B788")));
        dummyAirline.setCrew(Arrays.asList(new Crew(1, name, "Pilot", CrewType.PILOT)));

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
        assertThat(newAirline).isEqualToIgnoringGivenFields(retrievedAirline, "id");
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
                        .get(),
                firstAirline);

        //STEP 4.2: Comparing Second Airline: new with retrieved
        assertEqualAirline(
                Arrays.stream(airlineList)
                        .filter(airline -> airline.getName().equals(secondAirline.getName()))
                        .findAny()
                        .get(),
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