package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers;

import org.junit.jupiter.api.Test;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.http.client.methods.RequestBuilder;
import org.junit.jupiter.api.BeforeEach;


public class SetNegativePlayerScoreIntentHandlerTest {
	

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
		Object sut = new SetNegativePlayerScoreIntentHandler();
		assertEquals(sut.getClass(), SetNegativePlayerScoreIntentHandler.class);
	}

	@Test
	public void test_CanHandle() {
		RequestHandler sut = new SetNegativePlayerScoreIntentHandler();
		when(inputMock.matches(any(Predicate.class))).thenReturn(true);
		assertTrue(sut.canHandle(inputMock));
	}

	@Test
	public void test_NullHandle() {
		RequestHandler sut = new SetNegativePlayerScoreIntentHandler();
		assertThrows(NullPointerException.class, () -> sut.handle(null));
	}
	
	@Test
	public void test_NullScoreTable() {
		RequestHandler sut = new SetNegativePlayerScoreIntentHandler();
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
	public void test_EmptyScoreTable() {
		RequestHandler sut = new SetNegativePlayerScoreIntentHandler();
		AttributesManager attributeManager = mock(AttributesManager.class);
		Map<String, Object> persistentAttributes = new HashMap<String, Object>();
		Map<String, Slot> scoreTable = new HashMap<String, Slot>();
		persistentAttributes.put("ScoreTable", scoreTable);

		when(inputMock.getAttributesManager()).thenReturn(attributeManager);
		when(attributeManager.getPersistentAttributes()).thenReturn(persistentAttributes);
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

		Optional<Response> response = sut.handle(inputMock);
		assertTrue(response.isPresent());
		assertFalse(response.get().getShouldEndSession());
	}

	@Test
	public void test_ScoreTable() {
		RequestHandler sut = new SetNegativePlayerScoreIntentHandler();
		AttributesManager attributeManager = mock(AttributesManager.class);
		when(inputMock.getAttributesManager()).thenReturn(attributeManager);
		
		Map<String, Object> persistentAttributes = new HashMap<String, Object>();
		when(attributeManager.getPersistentAttributes()).thenReturn(persistentAttributes);
		
		Map<String, Long> scoreTable = new HashMap<String, Long>();
		persistentAttributes.put("ScoreTable", scoreTable);
		
		Map<String, Slot> slotMap = new HashMap<String, Slot>();
		slotMap.put("PlayerName", Slot.builder().withValue("Tom Tester").build());
		slotMap.put("Points", Slot.builder().withValue("15").build());
		Intent intent = Intent.builder().withSlots(slotMap).build();
		when(inputMock.getRequest()).thenReturn(IntentRequest.builder().withIntent(intent).build());
		
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());
		
		sut.handle(inputMock);
		
	}
	
	
	
}
