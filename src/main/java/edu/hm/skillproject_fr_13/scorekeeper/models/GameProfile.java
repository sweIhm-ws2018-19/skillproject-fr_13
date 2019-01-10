package edu.hm.skillproject_fr_13.scorekeeper.models;

import edu.hm.skillproject_fr_13.scorekeeper.interfaces.GamePoints;

public class GameProfile {

    private final String name;
    private final int startPoints;
    private final boolean hasPointsLimit;
    private final int pointsLimit;
    private final boolean isCountdown;
    
    public GameProfile(String name, int startPoints, boolean hasPointsLimit, int pointsLimit, boolean isCountdown) {
        this.name = name;
        this.startPoints = startPoints;
        this.hasPointsLimit = hasPointsLimit;
        this.pointsLimit = pointsLimit;
        this.isCountdown = isCountdown;
    }

    public String getName() {
        return this.name;
    }

    public int getStartPoints() {
        return this.startPoints;
    }

    public boolean hasPointsLimit() {
        return this.hasPointsLimit;
    }

    public int getPointsLimit() {
        return this.pointsLimit;
    }

    public boolean getIsCountdown() {
        return this.isCountdown;
    }
    
    public GamePoints getUsedGamePointsForGame() {
    	return new SingleGamePoints(0);
    }
}