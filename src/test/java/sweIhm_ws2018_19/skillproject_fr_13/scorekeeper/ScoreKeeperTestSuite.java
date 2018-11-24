package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper;
import sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ScoreKeeperTestSuite extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(ScoreKeeperTestSuite.class.getName());
		//$JUnit-BEGIN$
		// scorekeeper
		suite.addTestSuite(ScoreKeeperStreamHandlerTest.class);
		// scorekeeper.handlers
		suite.addTestSuite(CancelAndStopIntentHandlerTest.class);
		suite.addTestSuite(EndGameSessionIntentHandlerTest.class);
		suite.addTestSuite(GetPlayerScoresIntentHandlerTest.class);
		suite.addTestSuite(HelpIntentHandlerTest.class);
		suite.addTestSuite(LaunchRequestHandlerTest.class);
		suite.addTestSuite(SessionEndedRequestHandlerTest.class);
		suite.addTestSuite(SetNegativePlayerScoreIntentHandlerTest.class);
		suite.addTestSuite(SetPlayerScoreIntentHandlerTest.class);
		suite.addTestSuite(SetPositivePlayerScoreIntentHandlerTest.class);
		//$JUnit-END$
		return suite;
	}

}
