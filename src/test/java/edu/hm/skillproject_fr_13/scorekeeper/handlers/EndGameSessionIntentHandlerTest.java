package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import edu.hm.skillproject_fr_13.scorekeeper.handlers.EndGameSessionIntentHandler;

public class EndGameSessionIntentHandlerTest {


	private HandlerInput inputMock;

	@BeforeEach
	public void setup() {
		inputMock = mock(HandlerInput.class);
	}

	@Test
	public void test_Ctor() {
		Object sut = new EndGameSessionIntentHandler();
		assertEquals(sut.getClass(), EndGameSessionIntentHandler.class);
	}

	@Test
	public void test_CanHandle() {
		when(inputMock.matches(any())).thenReturn(true);
		RequestHandler sut = new EndGameSessionIntentHandler();
		assertTrue(sut.canHandle(inputMock));
	}

	@Test
	public void test_HandleMock() {
		RequestHandler sut = new EndGameSessionIntentHandler();

		AttributesManager attrMock = mock(AttributesManager.class);
		when(attrMock.getPersistentAttributes()).thenReturn(new HashMap<>());
		when(inputMock.getAttributesManager()).thenReturn(attrMock);

		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());
		Optional<Response> response = sut.handle(inputMock);
		assertTrue(response.isPresent());
		assertFalse(response.get().getShouldEndSession());
	}
	
	@Test
	public void test_HandleMockCorrect() {
		RequestHandler sut = new EndGameSessionIntentHandler();

		AttributesManager attrMock = mock(AttributesManager.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ScoreTable", new HashMap<String, Object>());
		
		when(attrMock.getPersistentAttributes()).thenReturn(map);
		when(inputMock.getAttributesManager()).thenReturn(attrMock);
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());
		
		Optional<Response> response = sut.handle(inputMock);
		assertTrue(response.isPresent());
		assertFalse(response.get().getShouldEndSession());
	}
	
}
