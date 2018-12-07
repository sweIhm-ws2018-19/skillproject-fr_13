package edu.hm.skillproject_fr_13.scorekeeper.models;

import java.util.HashMap;
import java.util.Map;

import edu.hm.skillproject_fr_13.scorekeeper.interfaces.GamePoints;

public class SingleGamePoints implements GamePoints {

    /**
     * Name of the points.
     */
    public static final String NAME = "Default";
    private final Map<String,Long> points;

    public SingleGamePoints(long points) {
        this.points = new HashMap<String,Long>();
        this.points.put(SingleGamePoints.NAME, points);
    }

    @Override
    public Map<String, Long> getPoints() {
        return this.points;
    }

    @Override
    public long getPointsByName(String name) {
        return this.points.get(SingleGamePoints.NAME);
    }

    @Override
    public void setPointsByName(String name, Long points) {
		this.points.put(SingleGamePoints.NAME, points);
	}

}