package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class HelpIntentHandler implements RequestHandler {
	
	public static final String HELP_NOSESSION =
			"Du kannst mich auffordern, eine Spielsitzung zu starten.";
	public static final String HELP_INSESSION =
			"Nenne mir den Namen eines Spielteilnehmers und seinen Punktestand oder frage mich" +
					"nach dem aktuellen Punktestand.";
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.HelpIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		final boolean isGameSessionRunning = (Boolean) input.getAttributesManager()
			.getSessionAttributes()
			.getOrDefault("isGameSessionRunning", false);
	
		return input.getResponseBuilder()
				.withSpeech(isGameSessionRunning ? HELP_INSESSION : HELP_NOSESSION)
				.build();
	}

}
