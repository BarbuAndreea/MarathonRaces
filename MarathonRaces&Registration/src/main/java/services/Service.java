package services;

import model.MarathonRace;
import model.Registration;
import repository.MarathonRaceRepo;
import repository.RegistrationRepo;
import repository.RepositoryException;

import java.util.List;

public class Service{

    public MarathonRaceRepo ar;

    private RegistrationRepo br;

    public Service(MarathonRaceRepo ar, RegistrationRepo br) {
        this.ar = ar;
        this.br = br;
    }

    public int addRace(String name, String type, String date, int distance) throws ServicesException{
        try {
            MarathonRace race = new MarathonRace(name, type, date, distance);
            MarathonRace newrace = ar.add(race);
            return newrace.getId();
        }catch (RepositoryException ex){
            throw new ServicesException("Error adding cake"+ex);
        }
    }

    public List<MarathonRace> getRaceByName(String name){
        return ar.findByName(name);
    }

    public List<Registration> getRegByRace(String name) {
        return br.filterByRace(name);
    }

    public void addRegister(String pname, String phn, String address, int age,MarathonRace race) throws ServicesException{
        try {
            Registration en = new Registration(pname, phn, address ,age, race);
            ar.update(race.getId(), race);
            br.add(en);
        }catch (RepositoryException er){
            throw  new ServicesException("Error adding registration"+er);
        }
    }
}

