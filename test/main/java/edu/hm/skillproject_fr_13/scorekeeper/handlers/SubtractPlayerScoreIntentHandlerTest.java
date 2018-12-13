package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

class SubtractPlayerScoreIntentHandlerTest {

	private HandlerInput inputMock;
	private SubtractPlayerScoreIntentHandler sut;

	@BeforeEach
	public void setup() {
		inputMock = mock(HandlerInput.class);
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

		sut = new SubtractPlayerScoreIntentHandler();
	}

	@Test
	public void test_CanHandle() {
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(sut.canHandle(inputMock));
	}

	@Test
	public void testCalculateNull() {
		assertThrows(NullPointerException.class, () -> sut.calculatePoints(null));
	}
	

	@Test
	public void handleScoreTableValidRequest() {
		final AttributesManager mockManager = mock(AttributesManager.class);
		final Map<String, Object> map = new HashMap<>();
		final Map<String, BigDecimal> scoreTable = new HashMap<>();
		scoreTable.put("Tom Tester", null);
		map.put("ScoreTable", scoreTable);

		final Map<String, Slot> slotMap = new HashMap<String, Slot>();
		slotMap.put("PlayerName", Slot.builder().withValue("Tom Tester").build());
		slotMap.put("Points", Slot.builder().withValue("20").build());
		final Intent intent = Intent.builder().withSlots(slotMap).build();

		when(inputMock.getAttributesManager()).thenReturn(mockManager);
		when(mockManager.getPersistentAttributes()).thenReturn(map);
		when(inputMock.getRequest()).thenReturn(IntentRequest.builder().withIntent(intent).build());

		final Optional<Response> response = sut.handle(inputMock);

		assertTrue(response.isPresent());
	}


}
