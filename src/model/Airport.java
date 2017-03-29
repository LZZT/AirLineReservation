package model;

import java.util.Set;

/**
 * Created by QQZhao on 3/5/17.
 */
public class Airport {

    private String code;
    private String name;

    private City city;

    private Set<Flight> departureFlightsSet;
    private Set<Flight> arrivalFlightsSet;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Flight> getDepartureFlightsSet() {
        return departureFlightsSet;
    }

    public void setDepartureFlightsSet(Set<Flight> departureFlightsSet) {
        this.departureFlightsSet = departureFlightsSet;
    }

    public Set<Flight> getArrivalFlightsSet() {
        return arrivalFlightsSet;
    }

    public void setArrivalFlightsSet(Set<Flight> arrivalFlightsSet) {
        this.arrivalFlightsSet = arrivalFlightsSet;
    }

}
