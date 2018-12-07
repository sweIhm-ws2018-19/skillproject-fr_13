package test.java.edu.hm.skillproject_fr_13.scorekeeper.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.hm.skillproject_fr_13.scorekeeper.models.GameProfile;

public class GameProfileTest {
	
	private final GameProfile sut;
	
	public GameProfileTest() {
		sut = new GameProfile("Tom", 0, true, 120, false);
	}

	@Test
	public void testCtorNameNull() {
		assertThrows(NullPointerException.class, ()->{new GameProfile(null, 0, false, 100, true);});
	}
	
	@Test
	public void testCtorIllegal() {
		assertThrows(IllegalArgumentException.class, () -> {new GameProfile("", 0, true, -1, false);});
	}
	
	@Test
	public void testGetInsertedName() {
		assertEquals(sut.getName(), "Tom");
	}
	
	@Test
	public void testPointLimit() {
		assertEquals(sut.getPointsLimit(), 120);
	}
	
	/**
	 * currently behaviour not actually specified.
	 */
	@Test
	public void testPointLimitIllegal() {
		GameProfile sut = new GameProfile("Herbert", 0, false, 5, false);
		assertThrows(IllegalArgumentException.class, ()-> {sut.getPointsLimit();});
	}
	
	@Test
	public void testGetIsCountdown() {
		assertEquals(sut.getIsCountdown(), false);
	}

}
