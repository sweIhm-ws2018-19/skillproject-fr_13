package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper;

// https://github.com/alexa/alexa-skills-kit-sdk-for-java/blob/2.0.x/ask-sdk-core/tst/com/amazon/ask/SkillTest.java

import org.junit.Test;

import com.amazon.ask.model.Context;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Session;
import com.amazon.ask.model.IntentRequest;

import junit.framework.TestCase;

public class ScoreKeeperStreamHandlerTest extends TestCase {
	
	private final static int DEFAULT_TIMEOUT = 1_000;

	@Test(timeout = DEFAULT_TIMEOUT)
	public void testEnabled() {
		assertEquals(true, true);
	}
	
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testBasic() {
		Context context = Context.builder()
					.withAudioPlayer(null)
					.withDisplay(null)
					.withSystem(null)
					.withViewport(null)
				.build();
		
		IntentRequest request = IntentRequest.builder()
					.withDialogState(null)
					.withIntent(null)
					.withLocale(null)
					.withRequestId(null)
					.withTimestamp(null)				
				.build();
		
		Session session = Session.builder()
					.withSessionId(null)
					.withAttributes(null)
					.withNew(null)
					.withSessionId(null)
					.withUser(null)
				.build();
		
		
		RequestEnvelope requestEnvelope = RequestEnvelope.builder()
					.withContext(context)
					.withRequest(request)
					.withSession(session)
				.withVersion(null)
		.build();
		
		ScoreKeeperStreamHandler streamHandler = new ScoreKeeperStreamHandler(); // fails due to missing country code
		
	}
	
	

}
