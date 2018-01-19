package xyz.lomasz.springhelloworld.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import xyz.lomasz.springhelloworld.dao.AirlineRepository;
import xyz.lomasz.springhelloworld.model.Airline;
import xyz.lomasz.springhelloworld.service.EsIndexService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AirlineControllerTest {

  @Mock
  private EsIndexService esIndexService;

  @Mock
  private AirlineRepository airlineRepository;


  @InjectMocks
  private AirlineController airlineController = new AirlineController(airlineRepository, esIndexService);

  @Test
  public void createAirlineNewShouldReturnCreatedStatus() throws Exception {
    // given
    Airline airline = new Airline();
    String icao = "ICAO";
    airline.setIcao(icao);

    // when
    ResponseEntity<?> result = airlineController.createAirline(airline);

    // then
    verify(airlineRepository, atLeastOnce()).save(airline);
    verify(esIndexService, atLeastOnce()).index(airline);
    assertEquals("/airline/" + icao, result.getHeaders().getLocation().toString());
    assertThat(result.getStatusCode(), is(HttpStatus.CREATED));

}

  @Test
  public void createAirlineDuplicatedShouldReturnConflictStatus() throws Exception {
    // given
    Airline airline = new Airline();
    String icao = "ICAO";
    airline.setIcao(icao);

    when(airlineRepository.findByIcao(airline.getIcao())).thenReturn(Optional.of(airline));

    // when
    ResponseEntity<?> result = airlineController.createAirline(airline);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.CONFLICT));

  }

  @Test(expected = IOException.class)
  public void createAirlineDuplicatedShouldThrowIOException() throws Exception {
    // given
    Airline airline = new Airline();
    String icao = "ICAO";
    airline.setIcao(icao);
    when(esIndexService.index(airline)).thenThrow(IOException.class);

    // when
    ResponseEntity<?> result = airlineController.createAirline(airline);

    // then
    fail("Expected exception");

  }

  @Test
  public void getAirlineExistedShouldReturnAirline() throws Exception {
    // given
    Airline airline = new Airline();
    String icao = "ICAO";
    airline.setIcao(icao);

    when(airlineRepository.findByIcao(icao)).thenReturn(Optional.of(airline));

    // when
    ResponseEntity<?> result = airlineController.getAirline(icao);

    // then
    assertThat(result.getBody(), is(airline));
    assertThat(result.getStatusCode(), is(HttpStatus.OK));

  }

  @Test
  public void getAirlineNotExistedShouldReturnNotFoundStatus() throws Exception {
    // given
    String icao = "ICAO";

    when(airlineRepository.findByIcao(icao)).thenReturn(Optional.empty());

    // when
    ResponseEntity<?> result = airlineController.getAirline(icao);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));

  }

  @Test
  public void deleteAirlineExistedShouldReturnOkStatus() throws Exception {
    // given
    Airline airline = new Airline();
    String icao = "ICAO";

    when(airlineRepository.findByIcao(icao)).thenReturn(Optional.of(airline));

    // when
    ResponseEntity<?> result = airlineController.deleteAirline(icao);

    // then
    verify(airlineRepository, atLeastOnce()).delete(icao);
    verify(esIndexService, atLeastOnce()).deleteIndex(airline);
    assertThat(result.getStatusCode(), is(HttpStatus.OK));

  }

  @Test
  public void deleteAirlineNotExistedShouldReturnNotFoundStatus() throws Exception {
    // given
    String icao = "ICAO";

    when(airlineRepository.findByIcao(icao)).thenReturn(Optional.empty());

    // when
    ResponseEntity<?> result = airlineController.deleteAirline(icao);

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));

  }

  @Test(expected = IOException.class)
  public void deleteAirlineNotExistedShouldThrowIOException() throws Exception {
    // given
    Airline airline = new Airline();
    String icao = "ICAO";

    when(airlineRepository.findByIcao(icao)).thenReturn(Optional.of(airline));
    when(esIndexService.deleteIndex(airline)).thenThrow(IOException.class);

    // when
    airlineController.deleteAirline(icao);

    // then
    fail("Expected exception");

  }


  @Test
  public void listAllAirlinesExistedShouldReturnAirlineListAndOkStatus() throws Exception {
    // TODO : Unit Test: listAllAirlines() -> List<Airline>
    // given
    Airline airline = mock(Airline.class);
    List<Airline> airlineList = Collections.singletonList(airline);

    when(airlineRepository.findAll()).thenReturn(airlineList);

    // when
    ResponseEntity<List<Airline>> result = airlineController.listAllAirlines();

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), is(airlineList));
    assertEquals(1, result.getBody().size());

  }

  @Test
  public void listAllAirlinesNotExistedShouldReturnNotFoundStatus() throws Exception {
    // TODO : Unit Test: listAllAirlines() -> empty List<Airline>
    // given
    List<Airline> airlineList = Collections.emptyList();

    when(airlineRepository.findAll()).thenReturn(airlineList);

    // when
    ResponseEntity<List<Airline>> result = airlineController.listAllAirlines();

    // then
    assertThat(result.getStatusCode(), is(HttpStatus.OK));
    assertThat(result.getBody(), is(airlineList));
    assertEquals(0, result.getBody().size());
  }

}