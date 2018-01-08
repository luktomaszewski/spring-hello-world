package xyz.lomasz.springhelloworld.handler;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@CommonsLog
@Service
public class JmsErrorHandler implements ErrorHandler {

  @Override
  public void handleError(Throwable t) {
    log.error(t.getMessage());
  }

}
