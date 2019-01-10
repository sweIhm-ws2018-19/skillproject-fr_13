package edu.hm.skillproject_fr_13.scorekeeper.interfaces;

import java.util.Map;

public interface GamePoints {

    public Map<String, Long> getPoints();

    public long  getPointsByName(String name);

    public void setPointsByName(String name, Long points);
    
}