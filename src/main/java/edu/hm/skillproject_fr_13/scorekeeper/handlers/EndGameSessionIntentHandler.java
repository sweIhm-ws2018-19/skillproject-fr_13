<<<<<<< HEAD
package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class EndGameSessionIntentHandler implements RequestHandler {
	
	public static final String CONFIRMATION = "Die Spielsitzung wurde beendet!";
	public static final String NO_SESSION = "Es läuft noch keine Spielsitzung.";
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("EndGameSessionIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		
		final Map<String, Object> persistentAttributes = input.getAttributesManager()
				.getPersistentAttributes();
		final String response;
		
		if (persistentAttributes.containsKey("ScoreTable")) {
			persistentAttributes.remove("ScoreTable");
			input.getAttributesManager().savePersistentAttributes();
			response = CONFIRMATION;
		}
		else
			response = NO_SESSION;

		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}

}
=======
package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class EndGameSessionIntentHandler implements RequestHandler {
	
	public static final String CONFIRMATION = "Die Spielsitzung wurde beendet!";
	public static final String NO_SESSION = "Es läuft noch keine Spielsitzung.";
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("EndGameSessionIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Object> persistentAttributes = input.getAttributesManager()
				.getPersistentAttributes();
		final String response;
		
		if (persistentAttributes.containsKey("ActivePlayers")) {
			persistentAttributes.remove("ActivePlayers");
			input.getAttributesManager().savePersistentAttributes();
			response = CONFIRMATION;
		}
		else
			response = NO_SESSION;

		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}

}
>>>>>>> 8ec73ec20685411b6932cfe2fe1f878bc790b7df
