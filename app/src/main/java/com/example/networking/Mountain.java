package com.example.networking;

@SuppressWarnings("WeakerAccess")
public class Mountain {


    private String ID;
    private String name;
    private String type;
    private String company;
    private String location;
    private String category;
    private int size;
    private int cost;
    //auxdata
    //private String wiki;
    //private String img;

    public Mountain(String ID, String name, String type, String company, String location, String category, int size, int cost) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.company = company;
        this.location = location;
        this.category = category;
        this.size = size;
        this.cost = cost;
    }
    public Mountain(String name) {
        this.name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getType() {
        return type;
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
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", size=" + size +
                ", cost=" + cost +
                '}';
    }
}
