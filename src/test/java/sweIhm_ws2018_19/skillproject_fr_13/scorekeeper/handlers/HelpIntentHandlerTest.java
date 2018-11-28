package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers;

import org.junit.jupiter.api.Test;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;


public class HelpIntentHandlerTest {
	
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
		Object sut = new HelpIntentHandler();
		assertEquals(sut.getClass(), HelpIntentHandler.class);
	}

	@Test
	public void test_CanHandle() {
		RequestHandler sut = new HelpIntentHandler();
		when(inputMock.matches(any(Predicate.class))).thenReturn(true);
		assertTrue(sut.canHandle(inputMock));
	}
	
	@Test
	public void test_ExistingScoreTable() {
		RequestHandler sut = new HelpIntentHandler();
		AttributesManager attributeManager = mock(AttributesManager.class);
		Map<String, Object> persistentAttributes = new HashMap<String, Object>();
		persistentAttributes.put("ScoreTable", new HashMap<String, Long>());

		when(inputMock.getAttributesManager()).thenReturn(attributeManager);
		when(attributeManager.getPersistentAttributes()).thenReturn(persistentAttributes);
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

		Optional<Response> response = sut.handle(inputMock);
		assertTrue(response.isPresent());
		assertFalse(response.get().getShouldEndSession());
	}
	
	@Test
	public void test_NonExistentScoreTable() {
		RequestHandler sut = new HelpIntentHandler();
		AttributesManager attributeManager = mock(AttributesManager.class);
		Map<String, Object> persistentAttributes = new HashMap<String, Object>();

		when(inputMock.getAttributesManager()).thenReturn(attributeManager);
		when(attributeManager.getPersistentAttributes()).thenReturn(persistentAttributes);
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

		Optional<Response> response = sut.handle(inputMock);
		assertTrue(response.isPresent());
		assertFalse(response.get().getShouldEndSession());
	}
}
