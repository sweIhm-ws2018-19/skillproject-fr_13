package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper;

import org.junit.Test;

import junit.framework.TestCase;
import com.amazon.ask.servlet.SkillServlet;

public class ScoreKeeperStreamHandlerTest extends TestCase{
	
	private final static int DEFAULT_TIMEOUT = 1_000;

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testEnabled() {
		assertEquals(true, true);
	}

}
