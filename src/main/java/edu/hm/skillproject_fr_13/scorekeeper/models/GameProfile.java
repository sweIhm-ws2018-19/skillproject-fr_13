package edu.hm.skillproject_fr_13.scorekeeper.models;

import edu.hm.skillproject_fr_13.scorekeeper.interfaces.GamePoints;

public class GameProfile {
    
    private final String name;
    private final GamePoints startPoints;
    private final boolean hasPointsLimit;
    private final GamePoints pointsLimit;
    private final boolean isCountdown;

    public GameProfile(String name, GamePoints startPoints, boolean hasPointsLimit, GamePoints pointsLimit, boolean isCountdown) {
        this.name = name;
        this.startPoints = startPoints;
        this.hasPointsLimit = hasPointsLimit;
        this.pointsLimit = pointsLimit;
        this.isCountdown = isCountdown;
    }

    public GameProfile(String name, GamePoints startPoints, boolean hasPointsLimit, boolean isCountdown) {
        this(name, startPoints, hasPointsLimit, null, isCountdown);
    }

    public String getName() {
        return this.name;
    }

    public GamePoints getStartPoints() {
        return this.startPoints;
    }

    public boolean hasPointsLimit() {
        return this.hasPointsLimit;
    }

    public GamePoints getPointsLimit() {
        return this.pointsLimit;
    }

    public boolean getIsCountdown() {
        return this.isCountdown;
    }
}