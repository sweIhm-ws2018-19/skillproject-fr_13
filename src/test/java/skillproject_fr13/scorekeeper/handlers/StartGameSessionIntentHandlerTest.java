package skillproject_fr13.scorekeeper.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import skillproject_fr13.scorekeeper.handlers.StartGameSessionIntentHandler;

public class StartGameSessionIntentHandlerTest {

	@Test
	public void testEnabled() {
		assertEquals(true, true);
	}

	private HandlerInput inputMock;

	@BeforeEach
	public void setup() {
		inputMock = mock(HandlerInput.class);
	}

	@Test
	public void test_Ctor() {
		Object sut = new StartGameSessionIntentHandler();
		assertEquals(sut.getClass(), StartGameSessionIntentHandler.class);
	}

	@Test
	public void test_CanHandle() {
		RequestHandler sut = new StartGameSessionIntentHandler();
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(sut.canHandle(inputMock));
	}

	@Test
	public void test_HandlerNoScoreTable() {

		RequestHandler sut = new StartGameSessionIntentHandler();

		final AttributesManager attrMock = mock(AttributesManager.class);
		when(inputMock.getAttributesManager()).thenReturn(attrMock);
		final Map<String, Object> persistentAttributes = new HashMap<>();
		when(attrMock.getPersistentAttributes()).thenReturn(persistentAttributes);

		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

		Optional<Response> response = sut.handle(inputMock);

		assertTrue(response.isPresent());
	}

	@Test
	public void test_HandlerWithScoreTable() {

		RequestHandler sut = new StartGameSessionIntentHandler();

		final AttributesManager attrMock = mock(AttributesManager.class);
		when(inputMock.getAttributesManager()).thenReturn(attrMock);
		final Map<String, Object> persistentAttributes = new HashMap<>();
		persistentAttributes.put("ScoreTable", null);
		when(attrMock.getPersistentAttributes()).thenReturn(persistentAttributes);

		final ResponseBuilder responseBuilder = new ResponseBuilder();
		when(inputMock.getResponseBuilder()).thenReturn(responseBuilder);

		Optional<Response> response = sut.handle(inputMock);

		assertTrue(response.isPresent());
	}

}
