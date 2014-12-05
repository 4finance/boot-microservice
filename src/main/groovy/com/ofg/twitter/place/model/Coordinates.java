package com.ofg.twitter.place.model;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {
    private List<Double> coordinates = new ArrayList<>();

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}


