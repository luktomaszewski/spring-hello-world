package xyz.lomasz.springhelloworld.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.lomasz.springhelloworld.dao.AirlineRepository;
import xyz.lomasz.springhelloworld.model.Airline;
import xyz.lomasz.springhelloworld.service.EsIndexService;

@RestController
@RequestMapping("/airline")
@Api(value = "Airline", description = "REST Service for Information about Airlines")
public class AirlineController {

  private AirlineRepository airlineRepository;
  private EsIndexService esIndexService;

  @Autowired
  public AirlineController(AirlineRepository airlineRepository, EsIndexService esIndexService) {
    this.airlineRepository = airlineRepository;
    this.esIndexService = esIndexService;
  }

  @ApiOperation(value = "Getting information about all airlines")
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<Airline>> listAllAirlines() {
    List<Airline> airlinesList = airlineRepository.findAll();
    if (airlinesList.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(airlinesList, HttpStatus.OK);
  }

  @ApiOperation(value = "Getting information about specific airline (finding by ICAO)")
  @RequestMapping(value = "{icao}", method = RequestMethod.GET)
  public ResponseEntity<?> getAirline(@PathVariable("icao") String icao) {
    Optional<Airline> airline = airlineRepository.findByIcao(icao);
    if (!airline.isPresent()) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Airline>(airline.get(), HttpStatus.OK);
  }

  @ApiOperation(value = "Adding new airline to service")
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> createAirline(@RequestBody Airline airline) throws IOException {
    if (airlineRepository.findByIcao(airline.getIcao()).isPresent()) {
      return new ResponseEntity(HttpStatus.CONFLICT);
    }

    airlineRepository.save(airline);

    esIndexService.index(airline);

    UriComponentsBuilder ucBuilder = UriComponentsBuilder.newInstance();
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/airline/{icao}").buildAndExpand(airline.getIcao()).toUri());
    return new ResponseEntity<String>(headers, HttpStatus.CREATED);
  }

  @ApiOperation(value = "Deleting airline from service")
  @RequestMapping(value = "{icao}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteAirline(@PathVariable("icao") String icao) throws IOException {
    Optional<Airline> airline = airlineRepository.findByIcao(icao);
    if (!airline.isPresent()) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    airlineRepository.delete(icao);

    esIndexService.deleteIndex(airline.get());

    return new ResponseEntity<Airline>(HttpStatus.OK);
  }
}

