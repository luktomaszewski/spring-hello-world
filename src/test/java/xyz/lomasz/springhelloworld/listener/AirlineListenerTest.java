package xyz.lomasz.springhelloworld.listener;

import static org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import xyz.lomasz.springhelloworld.controller.AirlineController;
import xyz.lomasz.springhelloworld.model.Airline;

@RunWith(MockitoJUnitRunner.class)
public class AirlineListenerTest {

  @Mock
  private AirlineController airlineController;

  @Mock
  Airline airline;

  @Test
  public void shouldReceiveMessageReturn() throws Exception {
    // given
    AirlineListener airlineListener = new AirlineListener();
    writeDeclaredField(airlineListener, "airlineController", airlineController, true);

    // when
    airlineListener.receiveMessage(airline);

    // then
    Mockito.verify(airlineController, Mockito.times(1)).createAirline(airline);
  }

}
