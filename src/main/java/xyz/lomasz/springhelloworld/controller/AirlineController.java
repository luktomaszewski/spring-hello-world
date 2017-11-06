package xyz.lomasz.springhelloworld.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.lomasz.springhelloworld.model.Airline;
import xyz.lomasz.springhelloworld.service.AirlineService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/airline")
@Api(value = "Airline", description = "REST Service for Information about Airlines")
public class AirlineController {

    private AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @ApiOperation(value = "Getting information about all airlines")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Airline>> listAllAirlines() {
        List<Airline> airlinesList = airlineService.findAll();
        if (airlinesList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Airline>>(airlinesList, HttpStatus.OK);
    }

    @ApiOperation(value = "Getting information about specific airline (finding by ID)")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAirline(@PathVariable("id") int id) {
        Optional<Airline> airline = airlineService.findById(id);
        if (!airline.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Airline>(airline.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Adding new airline to service")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAirline(@RequestBody Airline airline, UriComponentsBuilder ucBuilder) {
        if (airlineService.isExist(airline)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        airlineService.save(airline);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/airline/{id}").buildAndExpand(airline.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deleting airline from service")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAirline(@PathVariable("id") int id) {
        Optional<Airline> airline = airlineService.findById(id);
        if (!airline.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        airlineService.delete(id);
        return new ResponseEntity<Airline>(HttpStatus.OK);
    }
}

