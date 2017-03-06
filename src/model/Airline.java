package model;

import java.util.Set;

/**
 * Created by QQZhao on 3/5/17.
 */
public class Airline {

    private String name;
    private String code;

    private Set<Aircraft> aircraftSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Aircraft> getAircraftSet() {
        return aircraftSet;
    }

    public void setAircraftSet(Set<Aircraft> aircraftSet) {
        this.aircraftSet = aircraftSet;
    }
}
