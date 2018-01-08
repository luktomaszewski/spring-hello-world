package xyz.lomasz.springhelloworld.listener;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import xyz.lomasz.springhelloworld.controller.AirlineController;
import xyz.lomasz.springhelloworld.model.Airline;

@Component
public class AirlineListener {

  @Autowired
  private AirlineController airlineController;

  @JmsListener(destination = "xyz.lomasz.springhelloworld.topic.airline", containerFactory = "jmsListenerContainerFactory")
  public void receiveMessage(Airline airline) throws IOException {
    airlineController.createAirline(airline);
  }

}
