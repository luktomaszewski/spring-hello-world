package xyz.lomasz.springhelloworld.service;

import xyz.lomasz.springhelloworld.model.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService {

    Optional<Airline> findById(int id);

    Optional<Airline> findByName(String name);

    List<Airline> findAll();

    void save(Airline airline);

    void update(Airline airline);

    void delete(int id);

    boolean isExist(Airline airline);

}
