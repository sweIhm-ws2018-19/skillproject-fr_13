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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;


public class SessionEndedRequestHandlerTest {

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
		Object sut = new SessionEndedRequestHandler();
		assertEquals(sut.getClass(), SessionEndedRequestHandler.class);
	}

	@Test
	public void test_CanHandle() {
		RequestHandler sut = new SessionEndedRequestHandler();
		when(inputMock.matches(any(Predicate.class))).thenReturn(true);
		assertTrue(sut.canHandle(inputMock));
	}

	@Test
	public void test_NullHandle() {
		RequestHandler sut = new SessionEndedRequestHandler();
		assertThrows(NullPointerException.class, () -> sut.handle(null));
	}
	
	@Test
	public void test_EmptyScoreTable() {
		RequestHandler sut = new SessionEndedRequestHandler();
		when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

		Optional<Response> response = sut.handle(inputMock);
		assertTrue(response.isPresent());
	}
}
