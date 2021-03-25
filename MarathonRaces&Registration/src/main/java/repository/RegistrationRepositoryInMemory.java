package repository;

import model.Registration;

import java.util.List;
import java.util.stream.Collectors;


public class RegistrationRepositoryInMemory extends AbstractRepository<Integer, Registration> implements RegistrationRepo {
    @Override
    public List<Registration> filterByRace(String name) {
        return getAll().stream().filter(x->x.getRace().getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
}
