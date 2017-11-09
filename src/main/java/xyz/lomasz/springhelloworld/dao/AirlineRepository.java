package xyz.lomasz.springhelloworld.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xyz.lomasz.springhelloworld.model.Airline;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface AirlineRepository extends CrudRepository<Airline, Long> {
    List<Airline> findAll();

    Optional<Airline> findById(Long id);

    Optional<Airline> findByName(String name);

    boolean exists(Long id);
}
