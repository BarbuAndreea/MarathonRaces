package model;

public class Registration implements Identifiable<Integer>{
    private int Id, age;
    private String pname,phone, address;
    private MarathonRace race;

    public Registration() {
        this.Id = 0;
        this.pname = "";
        this.phone = "";
        this.address = "";
        this.race= new MarathonRace();
    }

    public Registration(int Id, String pname, String phone, String address, int age, MarathonRace race){
        this.Id = Id;
        this.pname= pname;
        this.phone = phone;
        this.address = address;
        this.age=age;
        this.race=race;
    }

    public Registration( String pname, String phone, String address, int age, MarathonRace race){
        this.pname= pname;
        this.phone = phone;
        this.address = address;
        this.age=age;
        this.race=race;
    }

    public Integer getId(){
        return Id;
    }

    public String getPname(){
        return pname;
    }

    public MarathonRace getRace(){
        return race;
    }

    public String getPhone(){
        return phone;
    }

    public String getAddress(){
        return address;
    }

    public int getAge(){
        return age;
    }

    public void setId(Integer Id){
        this.Id=Id;
    }

    public void setPname(String pname){ this.pname=pname; }

    public void setPhone(String phone){ this.phone=phone; }

    public void setAddress(String address){ this.address=address; }

    public void setRace(MarathonRace race){
        this.race = race;
    }

    public void setAge(int age){
        this.age=age;
    }

    public String toString(){
        String str = Id + "," + pname +","+ phone + "," + address+ ','+ age+ ','+ race;
        return str;

    }

}
