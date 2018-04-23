package com.example.avis.pedestrianapp;

public class EcoStation {
    private int id;
    private String name;
    private String geom;
    private String directionName;
    private String type;

    @Override
    public String toString() {
        return "EcoStation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", geom='" + geom + '\'' +
                ", directionName='" + directionName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
