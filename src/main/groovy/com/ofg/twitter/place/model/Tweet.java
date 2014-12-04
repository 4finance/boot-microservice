package com.ofg.twitter.place.model;

public class Tweet {
    private String id_str;
    private Coordinates coordinates;
    private TwitterPlace place;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public TwitterPlace getPlace() {
        return place;
    }

    public void setPlace(TwitterPlace place) {
        this.place = place;
    }
}
