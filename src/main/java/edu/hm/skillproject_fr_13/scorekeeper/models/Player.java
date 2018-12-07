package edu.hm.skillproject_fr_13.scorekeeper.models;

import edu.hm.skillproject_fr_13.scorekeeper.interfaces.GamePoints;
import edu.hm.skillproject_fr_13.scorekeeper.interfaces.GenericPlayer;

/**
 * 
 * @author HoferAnton
 *
 */
public class Player implements GenericPlayer{
	
	/**
	 * Immutable name of the Player.
	 */
	private final String playerName;
	
	/**
	 * GamePoints mutable since different game sessions can be expected with the same Player.
	 */
	private GamePoints playerPoints;

	/**
	 * Constructor of the Player.
	 * @param name defining the individual players name.
	 */
	public Player(final String name) {
		playerName = name;
	}

	/**
	 * getter of the player's name.
	 * @return
	 */
	@Override
	public String getName() {
		return playerName;
	}

	/**
	 * return game points of one player.
	 * @return
	 */
	public GamePoints getPlayerPoints() {
		return playerPoints;
	}

	/**
	 * Game points shall be changed according to the game played.
	 * @param playerPoints
	 */
	public void setPlayerPoints(GamePoints playerPoints) {
		this.playerPoints = playerPoints;
	}

	/**
	 * Equals checking for same class, name and GamePoints.
	 */
	@Override
	public boolean equals(Object obj) {
		if(getClass() != obj.getClass())
			return false;
		else {
			Player other = (Player) obj;
			return getName().equals(other.getName());
		}
	}
	
	@Override
	public int hashCode() {
		int hash = super.hashCode();
		hash = hash + getName().hashCode()*7;
		return hash;
	}
	
}
