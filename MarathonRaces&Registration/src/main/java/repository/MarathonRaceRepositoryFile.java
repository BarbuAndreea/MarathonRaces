package repository;
import model.MarathonRace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MarathonRaceRepositoryFile extends MarathonRaceRepositoryInMemory {
    private String FileName;
    private static int idGenerator=1;
    public MarathonRaceRepositoryFile(String s) {
        this.FileName = s;
        ReadFromFile();
    }

    private void ReadFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(FileName))) {
            //String line = null;
            String line=br.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid value for idGenerator, starting from 0");
            }
            while((line = br.readLine())!=null) {
                String[] el = line.split(";");
                if (el.length != 5) {
                    System.err.println("Line is not valid." + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(el[0]);
                    int d = Integer.parseInt(el[4]);
                    MarathonRace obj = new MarathonRace(id, el[1], el[2], el[3], d);
                    super.add(obj);
                } catch(NumberFormatException nr) {
                    System.err.println("Id not valid." + el[0]); }
            }
        }
        catch(IOException ex) {
            throw new RepositoryException("Error reading" + ex); }
    }

    private void writeToFile()
    {
        try(PrintWriter pw = new PrintWriter(FileName))
        {
            pw.println(idGenerator);
            for(MarathonRace obj: findAll())
            {
                String str = obj.getId() + ";" + obj.getName() + ";" + obj.getType() +";" + obj.getDate() + ";" + obj.getDistance();
                pw.println(str);
            }
        }catch(IOException ex) { throw new RepositoryException("error" + ex);
        }

    }

    @Override
    public MarathonRace add(MarathonRace m){
        m.setId(getNextId());
        super.add(m);
        writeToFile();
        return m;
    }

    private static int getNextId(){
        return idGenerator++;
    }

    @Override
    public void delete(MarathonRace m)
    {
        try  {
            super.delete(m);
            writeToFile();
        }
        catch(RuntimeException ex){throw new RepositoryException(ex);}
    }

    @Override
    public void update(Integer id, MarathonRace m)
    {
        try {
            super.update(id,m);
            writeToFile();
        }
        catch(RuntimeException ex) {throw new RepositoryException(ex);}
    }
}