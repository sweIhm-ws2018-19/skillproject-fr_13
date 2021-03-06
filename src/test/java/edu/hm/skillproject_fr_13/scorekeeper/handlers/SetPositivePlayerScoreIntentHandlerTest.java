package edu.hm.skillproject_fr_13.scorekeeper.handlers;


import static com.amazon.ask.request.Predicates.intentName;
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
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import edu.hm.skillproject_fr_13.scorekeeper.handlers.SetPositivePlayerScoreIntentHandler;


public class SetPositivePlayerScoreIntentHandlerTest {
	
	private HandlerInput inputMock;

	@BeforeEach
	public void setup() {
		inputMock = mock(HandlerInput.class);
	}

	@Test
	public void test_Ctor() {
		Object sut = new SetPositivePlayerScoreIntentHandler();
		assertEquals(sut.getClass(), SetPositivePlayerScoreIntentHandler.class);
	}

	@Test
	public void test_CanHandle() {
		RequestHandler sut = new SetPositivePlayerScoreIntentHandler();
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(sut.canHandle(inputMock));
	}


	@Test
	public void testEmptyScoreTableInvalidRequest() {
		RequestHandler sut = new SetPositivePlayerScoreIntentHandler();
		
		AttributesManager attributeManager = mock(AttributesManager.class);
		Map<String, Object> persistentAttributes = new HashMap<String, Object>();
		Map<String, Long> scoreTable = new HashMap<String, Long>();
		persistentAttributes.put("ScoreTable", scoreTable);

		Map<String, Slot> slotMap = new HashMap<String, Slot>();
		slotMap.put("PlayerName", Slot.builder().withValue("Tom Tester").build());
		slotMap.put("Points", Slot.builder().withValue("15").build());
		Intent intent = Intent.builder().withSlots(slotMap).build();
		
		when(inputMock.getAttributesManager()).thenReturn(attributeManager);
		when(attributeManager.getPersistentAttributes()).thenReturn(persistentAttributes);
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());
		when(inputMock.getRequest()).thenReturn(IntentRequest.builder().withIntent(intent).build());
		
		sut.handle(inputMock);
		assertTrue(scoreTable.isEmpty());
	}
	
	@Test
	public void testScoreTableInvalidRequest() {
		RequestHandler sut = new SetPositivePlayerScoreIntentHandler();
		
		AttributesManager attributeManager = mock(AttributesManager.class);
		Map<String, Object> persistentAttributes = new HashMap<String, Object>();
		Map<String, Long> scoreTable = new HashMap<String, Long>();
		scoreTable.put("Max", AddPlayerIntentHandler.DEFAULT_POINT_VALUE);
		persistentAttributes.put("ScoreTable", scoreTable);

		Map<String, Slot> slotMap = new HashMap<String, Slot>();
		slotMap.put("PlayerName", Slot.builder().withValue("Tom Tester").build());
		slotMap.put("Points", Slot.builder().withValue("15").build());
		Intent intent = Intent.builder().withSlots(slotMap).build();
		
		when(inputMock.getAttributesManager()).thenReturn(attributeManager);
		when(attributeManager.getPersistentAttributes()).thenReturn(persistentAttributes);
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());
		when(inputMock.getRequest()).thenReturn(IntentRequest.builder().withIntent(intent).build());
		
		sut.handle(inputMock);
		final int requiredTableSize = 1;
		assertEquals(scoreTable.size(), requiredTableSize);
	}
	@Test
	public void testScoreTableValidRequest() {
		RequestHandler sut = new SetPositivePlayerScoreIntentHandler();
		
		AttributesManager attributeManager = mock(AttributesManager.class);
		Map<String, Object> persistentAttributes = new HashMap<String, Object>();
		Map<String, Long> scoreTable = new HashMap<String, Long>();
		scoreTable.put("Tom Tester", AddPlayerIntentHandler.DEFAULT_POINT_VALUE);
		persistentAttributes.put("ScoreTable", scoreTable);

		Map<String, Slot> slotMap = new HashMap<String, Slot>();
		slotMap.put("PlayerName", Slot.builder().withValue("Tom Tester").build());
		slotMap.put("Points", Slot.builder().withValue("15").build());
		Intent intent = Intent.builder().withSlots(slotMap).build();
		
		when(inputMock.getAttributesManager()).thenReturn(attributeManager);
		when(attributeManager.getPersistentAttributes()).thenReturn(persistentAttributes);
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());
		when(inputMock.getRequest()).thenReturn(IntentRequest.builder().withIntent(intent).build());
		
		sut.handle(inputMock);
		final int requiredTableSize = 1;
		assertEquals(scoreTable.size(), requiredTableSize);
		assertTrue(scoreTable.get("Tom Tester").equals(new Long(15)));
	}
	
	
}
