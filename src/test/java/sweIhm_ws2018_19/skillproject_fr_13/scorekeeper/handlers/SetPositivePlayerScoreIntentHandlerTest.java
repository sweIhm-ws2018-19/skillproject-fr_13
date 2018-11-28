package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers;

import org.junit.jupiter.api.Test;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;


public class SetPositivePlayerScoreIntentHandlerTest {
	

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
		Object sut = new SetPositivePlayerScoreIntentHandler();
		assertEquals(sut.getClass(), SetPositivePlayerScoreIntentHandler.class);
	}

	@Test
	public void test_CanHandle() {
		RequestHandler sut = new SetPositivePlayerScoreIntentHandler();
		when(inputMock.matches(any(Predicate.class))).thenReturn(true);
		assertTrue(sut.canHandle(inputMock));
	}

	@Test
	public void test_nullHandle() {
		RequestHandler sut = new SetPositivePlayerScoreIntentHandler();
		assertThrows(NullPointerException.class, () -> sut.handle(null));
	}
	
	
}
