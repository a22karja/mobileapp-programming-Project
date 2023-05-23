package com.example.networking;

@SuppressWarnings("WeakerAccess")
public class Plant {



    private String ID;
    //JUst an ID

    private String name;
    //Name of Plant

    private String company;
    //When it was waterd last

    private String location;
    //Where the plant is

    private String category;
    //What the plant gives

    private int size;
    //Empty

    private int cost;
    //How often in days it should be watered

    public Plant(String ID, String name, String type, String company, String location, String category, int size, int cost) {
        this.ID = ID;
        this.name = name;
        this.company = company;
        this.location = location;
        this.category = category;
        this.size = size;
        this.cost = cost;
    }

    //Nedan för är auto genererade getters och setters samt en constructor för Mountain
    public Plant(String name) {
        this.name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public int getSize() {
        return size;
    }

    public int getCost() {
        return cost;
    }


    @Override
    public String toString() {
        return "Mountain{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", size=" + size +
                ", cost=" + cost +
                '}';
    }
}
