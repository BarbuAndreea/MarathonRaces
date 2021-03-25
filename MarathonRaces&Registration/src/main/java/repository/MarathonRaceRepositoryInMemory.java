package repository;

import model.MarathonRace;
import java.util.List;
import java.util.stream.Collectors;

public class MarathonRaceRepositoryInMemory extends AbstractRepository< Integer, MarathonRace> implements MarathonRaceRepo {

    @Override
    public List<MarathonRace> findAllraces() {
        return getAll().stream().collect(Collectors.toList());
    }
    @Override
    public List<MarathonRace> findByName(String name) {
        return getAll().stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
}
