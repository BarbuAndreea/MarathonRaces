package model;

public class MarathonRace implements Identifiable<Integer>{
    private int Id, distance;
    private String name, type, date;

    public MarathonRace() {
        this.Id = 0;
        this.name = "";
        this.type = "";
        this.date = "";
        this.distance = 0;
    }

    public MarathonRace(int Id, String name, String type, String date, int distance){
        this.Id = Id;
        this.name = name;
        this.type = type;
        this.date = date;
        this.distance = distance;
    }

    public MarathonRace(String name, String type, String date, int distance){
        this.type = type;
        this.name = name;
        this.date = date;
        this.distance = distance;
    }

    public Integer getId(){
        return Id;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getDate(){
        return date;
    }

    public int getDistance(){
        return distance;
    }

    public void setId(Integer Id){
        this.Id=Id;
    }

    public void setDistance(int distance){
        this.distance=distance;
    }

    public void setName(String name){ this.name=name; }

    public void setType(String type){
        this.type=type;
    }

    public void setDate(String date){
        this.date=date;
    }

    public String toString(){
        String str = Id + "," + name + "," + type + "," + date + "," + distance;
        return str;

    }

}

