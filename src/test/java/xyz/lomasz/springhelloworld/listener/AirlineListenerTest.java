package xyz.lomasz.springhelloworld.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import xyz.lomasz.springhelloworld.controller.AirlineController;
import xyz.lomasz.springhelloworld.model.Airline;

import static org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField;

@RunWith(MockitoJUnitRunner.class)
public class AirlineListenerTest {

    @Mock
    private AirlineController airlineController;

    @Mock
    private Airline airline;

    @Test
    public void receiveMessageShouldCreateAirline() throws Exception {
        // given
        AirlineListener airlineListener = new AirlineListener();
        writeDeclaredField(airlineListener, "airlineController", airlineController, true);

        // when
        airlineListener.receiveMessage(airline);

        // then
        Mockito.verify(airlineController, Mockito.times(1)).createAirline(airline);
    }

}
