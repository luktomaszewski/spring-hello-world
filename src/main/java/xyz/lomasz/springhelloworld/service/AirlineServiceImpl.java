package xyz.lomasz.springhelloworld.service;

import org.springframework.stereotype.Service;
import xyz.lomasz.springhelloworld.model.Airline;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service("airlineService")
public class AirlineServiceImpl implements AirlineService {

    private static final AtomicInteger counter = new AtomicInteger();
    private static List<Airline> airlines = new ArrayList<>();

    @Override
    public Optional<Airline> findById(int id) {
        return airlines.stream()
                .filter(airline -> airline.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<Airline> findByName(String name) {
        return airlines.stream()
                .filter(airline -> name.equals(airline.getName()))
                .findFirst();
    }

    @Override
    public List<Airline> findAll() {
        return airlines;
    }

    @Override
    public void save(Airline airline) {
        airline.setId(counter.incrementAndGet());
        airlines.add(airline);
    }

    @Override
    public void update(Airline airline) {
        int index = airlines.indexOf(airline);
        airlines.set(index, airline);
    }

    @Override
    public void delete(int id) {
        airlines.removeIf(airline -> airline.getId() == id);
    }

    @Override
    public boolean isExist(Airline airline) {
        return findByName(airline.getName()).isPresent();
    }
}
