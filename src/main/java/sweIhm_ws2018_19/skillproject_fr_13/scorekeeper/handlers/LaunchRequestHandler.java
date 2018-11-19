package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {
	
	public static final String WELCOME_MSG =
			"Willkommen im Score Keeper! Du kannst jetzt eine Spielsitzung starten.";

	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(LaunchRequest.class));
	}

	public Optional<Response> handle(HandlerInput input) {
		return input.getResponseBuilder()
				.withSpeech(WELCOME_MSG)
				.withReprompt(WELCOME_MSG)
				.build();
	}

}
