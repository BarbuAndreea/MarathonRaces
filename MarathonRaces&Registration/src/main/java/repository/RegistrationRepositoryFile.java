package repository;

import model.MarathonRace;
import model.Registration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistrationRepositoryFile extends RegistrationRepositoryInMemory {
    private String FileName;
    private MarathonRaceRepo aRepo;
    private static int idGenerator=1;

    public RegistrationRepositoryFile(String s, MarathonRaceRepo aRepo) {
        this.FileName = s;
        this.aRepo=aRepo;
        ReadFromFile();
    }

    private static int getNextId(){
        return idGenerator++;
    }

    private void ReadFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(FileName))) {
            String line=br.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator, starting from 0");
            }
            while((line = br.readLine())!=null) {
                String[] el = line.split(";");
                if (el.length != 6) {
                    System.err.println("Line is not valid." + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(el[0]);
                    int age = Integer.parseInt(el[4]);
                    int raceId = Integer.parseInt(el[5]);
                    MarathonRace race = aRepo.findById(raceId);
                    Registration o = new Registration(id, el[1], el[2], el[3], age, race);
                    super.add(o);
                }
                catch(NumberFormatException nr) {
                    System.err.println("Id not valid." + el[0]+ nr); }
            }
        }
        catch(IOException ex) {
            throw new RepositoryException("Error" + ex); }
    }

    private void writeToFile()
    {
        try(PrintWriter pw = new PrintWriter(FileName))
        {
            pw.println(idGenerator);
            for(Registration obj: findAll())
            {
                String str = obj.getId() + ";" + obj.getPname() + ";" + obj.getPhone() +";" + obj.getAddress()+ ";" + obj.getAge() +";" + obj.getRace().getId() ;
                pw.println(str);
            }
        }catch(IOException ex) { throw new RepositoryException("error" + ex);
        }
    }

    @Override
    public Registration add(Registration m){
        m.setId(getNextId());
        super.add(m);
        writeToFile();
        return m;
    }

    @Override
    public void delete(Registration m)
    {
        try  {
            super.delete(m);
            writeToFile();
        }
        catch(RuntimeException ex){throw new RepositoryException(ex);}
    }

    @Override
    public void update(Integer id, Registration m)
    {
        try {
            super.update(id,m);
            writeToFile();
        }
        catch(RuntimeException ex) {throw new RepositoryException(ex);}
    }
}