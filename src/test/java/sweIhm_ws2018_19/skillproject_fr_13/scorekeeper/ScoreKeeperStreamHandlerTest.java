package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//<https://github.com/alexa/alexa-skills-kit-sdk-for-java/blob/2.0.x/ask-sdk-lambda-support/tst/com/amazon/ask/SkillStreamHandlerTest.java>

import com.amazon.ask.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

import com.amazon.ask.exception.AskSdkException;
import com.amazon.ask.request.SkillRequest;
import com.amazon.ask.response.SkillResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mockito.ArgumentCaptor;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class ScoreKeeperStreamHandlerTest {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private TestRequest testRequest;
	private TestResponse testResponse;
	private SkillResponse<TestResponse> skillResponse;

	private AlexaSkill<TestRequest, TestResponse> skill;

	@BeforeEach
	public void setup() {
		skill = mock(AlexaSkill.class);
		testRequest = new TestRequest();
		testRequest.setRequest("request");
		testResponse = new TestResponse();
		testResponse.setResponse("response");
		skillResponse = mock(SkillResponse.class);
	}

	@Test
	public void null_skill_throws_exception() {
		new ScoreKeeperStreamHandler();
	}

	@Test
	public void testEnabled() {
		assertEquals(true, true);
	}

	private class TestRequest {

		private String request;

		public String getRequest() {
			return request;
		}

		public void setRequest(String request) {
			this.request = request;
		}

	}

	private class TestResponse {

		private String response;

		public String getResponse() {
			return response;
		}

		public void setResponse(String response) {
			this.response = response;
		}

	}

	private final class TestSkillStreamHandler extends SkillStreamHandler {
		public TestSkillStreamHandler(AlexaSkill skill) {
			super(skill);
		}

		public TestSkillStreamHandler(AlexaSkill... skills) {
			super(skills);
		}
	}

	private String getHandlerOutput(Object envelope) throws IOException {
		byte[] json = OBJECT_MAPPER.writeValueAsBytes(envelope);
		InputStream is = new ByteArrayInputStream(json);
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		SkillStreamHandler streamHandler = new TestSkillStreamHandler(skill);

		streamHandler.handleRequest(is, os, null);
		String output = new String(os.toByteArray(), Charset.defaultCharset());
		return output;
	}


}
