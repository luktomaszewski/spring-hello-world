package xyz.lomasz.springhelloworld.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.lomasz.springhelloworld.controller.AirlineController;
import xyz.lomasz.springhelloworld.model.Airline;

@Component
public class AirlineListener {

  private AirlineController airlineController;

  @Autowired
  public AirlineListener(
      AirlineController airlineController) {
    this.airlineController = airlineController;
  }

  @JmsListener(destination = "airline")
  public void receiveMessage(String messageString) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Airline airline = mapper.readValue(messageString, Airline.class);
    airlineController.createAirline(airline);
  }

}
