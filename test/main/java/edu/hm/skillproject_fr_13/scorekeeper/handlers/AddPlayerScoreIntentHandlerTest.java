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
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

class AddPlayerScoreIntentHandlerTest {

	private HandlerInput inputMock;
	private AddPlayerScoreIntentHandler sut;

	@BeforeEach
	public void setup() {
		inputMock = mock(HandlerInput.class);
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

		sut = new AddPlayerScoreIntentHandler();
	}

	@Test
	public void test_CanHandle() {
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(sut.canHandle(inputMock));
	}

}
