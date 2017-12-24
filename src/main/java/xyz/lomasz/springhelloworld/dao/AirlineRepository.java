package xyz.lomasz.springhelloworld.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.lomasz.springhelloworld.model.Airline;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, String> {

  List<Airline> findAll();

  Optional<Airline> findByIcao(String icao);

}
