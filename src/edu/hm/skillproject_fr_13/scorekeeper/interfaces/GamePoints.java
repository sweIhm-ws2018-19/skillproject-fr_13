package edu.hm.skillproject_fr_13.scorekeeper.interfaces;

import java.util.Map;

public interface GamePoints {

    public Map<String,Integer> getPoints();

    public int  getPointsByName(String name);

    public void setPointsByName(String name, int points);
    
}