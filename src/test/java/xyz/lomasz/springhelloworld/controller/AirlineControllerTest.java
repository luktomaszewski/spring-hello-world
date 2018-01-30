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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AirlineControllerTest {

    @Mock
    private AirlineRepository airlineRepository;


    @InjectMocks
    private AirlineController airlineController = new AirlineController(airlineRepository);

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

    @Test
    public void listAllAirlinesExistedShouldReturnAirlineListAndOkStatus() throws Exception {
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