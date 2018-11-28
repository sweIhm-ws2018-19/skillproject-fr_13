package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amazon.ask.response.ResponseBuilder;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.Optional;
import java.util.function.Predicate;


public class CancelAndStopIntentHandlerTest {
	

	@Test
	public void test_Enabled() {
		assertEquals(true, true);
	}
	
	
	private HandlerInput inputMock;
	
	@BeforeEach
	public void setup() {
		inputMock = mock(HandlerInput.class);
	}
	
	@Test
	public void test_Ctor() {
		Object sut = new CancelAndStopIntentHandler();
		assertEquals(sut.getClass(), CancelAndStopIntentHandler.class);
	}

	@Test
	public void test_CanHandle() {
		RequestHandler sut = new CancelAndStopIntentHandler();
		when(inputMock.matches(any(Predicate.class))).thenReturn(true);
		assertTrue(sut.canHandle(inputMock));
	}

	@Test
	public void test_handleCancelAndStop() {
		
		RequestHandler sut = new CancelAndStopIntentHandler();
		final ResponseBuilder responseBuilder = new ResponseBuilder();
		when(inputMock.getResponseBuilder()).thenReturn(responseBuilder);
		Optional<Response> response = sut.handle(inputMock);
		assertTrue(response.isPresent());
		assertTrue(response.get().getShouldEndSession());
	}
	
}
