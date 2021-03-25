package repository;

import model.MarathonRace;

import java.util.List;

public interface MarathonRaceRepo extends Repository<Integer, MarathonRace>{
    List<MarathonRace> findByName(String name);
    List<MarathonRace> findAllraces();
}
