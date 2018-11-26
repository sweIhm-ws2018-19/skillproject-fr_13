package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.amazon.ask.SkillStreamHandler;
import com.amazonaws.services.lambda.runtime.Context;



class ScoreKeeperStreamHandlerTest {

	
	@Test
	public void testEnabled() {
		assertEquals(true, true);
	}
	
	@InjectMocks
	private SkillStreamHandler scoreKeeperHandlerMock; 
	
	@Captor
	private ArgumentCaptor<InputStream> inputStreamArg;
	@Captor
	private ArgumentCaptor<OutputStream> outputStreamArg;
	@Captor
	private ArgumentCaptor<Context> contextArg;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test() throws IOException{
		verify(scoreKeeperHandlerMock).handleRequest(inputStreamArg.capture(), outputStreamArg.capture(), contextArg.capture());
		scoreKeeperHandlerMock.handleRequest(null, null, null);
	}

	
	// tests from original java sdk
{

//	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//	private AlexaSkill<RequestEnvelope, ResponseEnvelope> skill;
//	
//	@Before
//	public void setup() {
//		skill = mock(AlexaSkill.class);
//	}
//	
//	@Test
//	public void null_skill_throws_exception() {
//		assertThrows(IllegalArgumentException.class, () -> {new TestSkillStreamHandler((AlexaSkill)null);});
//	}
//	
//	private String getHandlerOutput(Object envelope) throws IOException{
//		byte[] json = OBJECT_MAPPER.writeValueAsBytes(envelope);
//		InputStream is = new ByteArrayInputStream(json);
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		
//		SkillStreamHandler streamHandler = new TestSkillStreamHandler(skill);
//		streamHandler.handleRequest(is, os, null);
//		String output = new String(os.toByteArray(), Charset.defaultCharset());
//		return output;
//	}
//	
//	private final class TestSkillStreamHandler extends SkillStreamHandler{
//		public TestSkillStreamHandler(AlexaSkill skill) {
//			super(skill);
//		}
//		
//		public TestSkillStreamHandler(AlexaSkill... skills) {
//			super(skills);
//		}
//	}
	}
}
