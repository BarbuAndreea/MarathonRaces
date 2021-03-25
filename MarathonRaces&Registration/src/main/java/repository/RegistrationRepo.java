package repository;

import model.Registration;

import java.util.List;

public interface RegistrationRepo extends Repository<Integer, Registration> {
    List<Registration> filterByRace(String name);
}
