package com.example.demo.user;

public abstract class user {
    
    protected int ID;
    protected int Age;
    protected int Year;
    protected String Name;
    protected String Email;

    public user(int ID, int Age, int Year, String Name,  String Email){
    this.ID = ID;
    this.Age = Age;
    this.Year = Year;
    this.Name = Name;
    this.Email = Email;
    }

    
    public int getID() {
        return ID;
    }

    public int getAge(){
        return Age;
    }

    public int getYear(){
        return Year;
    }

    public String getName(){
        return Name;
    }

    public String getEmail(){
        return Email;
    }
    
    public void setAge(int Age){
        this.Age = Age;
    }

    public void setYear(int Year){
        this.Year = Year;
    }
    
    public void setName(String Name){
        this.Name = Name;
    }

    public void setEmail(String Email){
        this.Email = Email;
    }


}
