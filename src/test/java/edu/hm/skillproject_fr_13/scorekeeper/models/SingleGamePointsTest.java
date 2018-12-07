package edu.hm.skillproject_fr_13.scorekeeper.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.hm.skillproject_fr_13.scorekeeper.models.SingleGamePoints;

public class SingleGamePointsTest {

	private SingleGamePoints sutPrime;
	private final int defaultPoints = 0;

	@BeforeEach
	public void setup() {
		sutPrime = new SingleGamePoints(defaultPoints);
	}

	@Test
	public void testGetPointsWithDefault() {
		final long expected = defaultPoints;
		final long actual = sutPrime.getPoints().get(SingleGamePoints.NAME);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetPointsByName() {
		final long expected = defaultPoints;
		final long actual = sutPrime.getPointsByName(SingleGamePoints.NAME);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSetByName() {
		final long expected = 15;
		sutPrime.setPointsByName(SingleGamePoints.NAME, (long) expected);
		final long actual = sutPrime.getPointsByName(SingleGamePoints.NAME);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSetNewName() {
		final long expected = 15;
		final String testerName = "Tom";
		sutPrime.setPointsByName(testerName, expected);
		final long actual = sutPrime.getPointsByName(testerName);
		assertEquals(expected, actual);
	}
}
