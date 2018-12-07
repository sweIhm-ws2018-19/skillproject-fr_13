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
		final int expected = defaultPoints;
		final int actual = sutPrime.getPoints().get(SingleGamePoints.NAME);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetPointsByName() {
		final int expected = defaultPoints;
		final int actual = sutPrime.getPointsByName(SingleGamePoints.NAME);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMutabilityMap() {
		final int expected = defaultPoints;
		sutPrime.getPoints().put(SingleGamePoints.NAME, 3);
		final int actual = sutPrime.getPointsByName(SingleGamePoints.NAME);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSetByName() {
		final int expected = 15;
		sutPrime.setPointsByName(SingleGamePoints.NAME, expected);
		final int actual = sutPrime.getPointsByName(SingleGamePoints.NAME);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSetNewName() {
		final int expected = 15;
		final String testerName = "Tom";
		sutPrime.setPointsByName(testerName, expected);
		final int actual = sutPrime.getPointsByName(testerName);
		assertEquals(expected, actual);
	}
}
