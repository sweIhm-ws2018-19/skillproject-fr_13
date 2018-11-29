package com.edu.hm.skillproject_fr_13.scorekeeper.handlers;

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
import com.edu.hm.skillproject_fr_13.scorekeeper.handlers.GetPlayerScoresIntentHandler;

public class GetPlayerScoresIntentHandlerTest {

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
		Object sut = new GetPlayerScoresIntentHandler();
		assertEquals(sut.getClass(), GetPlayerScoresIntentHandler.class);
	}

	@Test
	public void test_CanHandle() {
		RequestHandler sut = new GetPlayerScoresIntentHandler();
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(sut.canHandle(inputMock));
	}

	@Test
	public void test_nullHandle() {
		RequestHandler sut = new GetPlayerScoresIntentHandler();
		assertThrows(NullPointerException.class, () -> sut.handle(null));
	}
	
	@Test
	public void test_nullScoreTable() {
		RequestHandler sut = new GetPlayerScoresIntentHandler();
		AttributesManager attributeManager = mock(AttributesManager.class);
		Map<String, Object> persistentAttributes = new HashMap<String, Object>();
		persistentAttributes.put("ScoreTable", null);

		when(inputMock.getAttributesManager()).thenReturn(attributeManager);
		when(attributeManager.getPersistentAttributes()).thenReturn(persistentAttributes);
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

		Optional<Response> response = sut.handle(inputMock);
		assertTrue(response.isPresent());
		assertFalse(response.get().getShouldEndSession());
	}

	@Test
	public void test_emptyScoreTable() {
		RequestHandler sut = new GetPlayerScoresIntentHandler();
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
	public void test_filledScoreTable() {
		RequestHandler sut = new GetPlayerScoresIntentHandler();
		AttributesManager attributeManager = mock(AttributesManager.class);
		Map<String, Object> persistentAttributes = new HashMap<String, Object>();
		HashMap<String, Long> map = new HashMap<String, Long>();
		map.put("Tom",(long) 20);
		map.put("Markus",(long) 50);
		persistentAttributes.put("ScoreTable", map);

		when(inputMock.getAttributesManager()).thenReturn(attributeManager);
		when(attributeManager.getPersistentAttributes()).thenReturn(persistentAttributes);
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

		Optional<Response> response = sut.handle(inputMock);
		assertTrue(response.isPresent());
		assertFalse(response.get().getShouldEndSession());
		
	}
	
	
}
