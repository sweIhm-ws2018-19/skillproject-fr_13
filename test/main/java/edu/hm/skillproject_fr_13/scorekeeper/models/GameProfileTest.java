package edu.hm.skillproject_fr_13.scorekeeper.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.hm.skillproject_fr_13.scorekeeper.models.GameProfile;

public class GameProfileTest {
	
	private final GameProfile sut;
	
	public GameProfileTest() {
		sut = new GameProfile("Tom", 0, true, 120, false);
	}

//	@Test
//	public void testCtorNameNull() {
//		assertThrows(NullPointerException.class, ()->{new GameProfile(null, 0, false, 100, true);});
//	}

//	/**
//	 * arangement is illogical.
//	 */
//	@Test
//	public void testCtorIllegal() {
//		assertThrows(IllegalArgumentException.class, () -> {new GameProfile("", 0, true, -1, false);});
//	}
	
	@Test
	public void testGetInsertedName() {
		assertEquals(sut.getName(), "Tom");
	}
	
	@Test
	public void testPointLimit() {
		assertEquals(sut.getPointsLimit(), 120);
	}
	
//	/**
//	 * currently behaviour not actually specified.
//	 */
//	@Test
//	@Disabled
//	public void testPointLimitIllegal() {
//		GameProfile sut = new GameProfile("Herbert", 0, false, 5, false);
//		assertThrows(IllegalArgumentException.class, ()-> {sut.getPointsLimit();});
//	}
	
	@Test
	public void testGetIsCountdown() {
		assertEquals(sut.getIsCountdown(), false);
	}

	@Test
	public void testGetStartPoints() {
		assertEquals(sut.getStartPoints(), 0);
	}
	
	@Test
	public void testHasPointsLimit() {
		assertTrue(sut.hasPointsLimit());
	}
}
