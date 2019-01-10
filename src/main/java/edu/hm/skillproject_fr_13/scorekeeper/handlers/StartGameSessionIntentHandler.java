<<<<<<< HEAD
package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class StartGameSessionIntentHandler implements RequestHandler {

	public static final String CONFIRMATION = "Die Spielsitzung wurde gestartet. "
			+ "<say-as interpret-as=\"interjection\">Viel Glück</say-as>!";
	public static final String SESSION_RUNNING = "Es läuft bereits eine Spielsitzung. "
			+ "Du must die letzte Spielsitzung beenden, " + "bevor du eine neue starten kannst.";

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("StartGameSessionIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
		final String response;

		if (persistentAttributes.containsKey("ScoreTable"))
			response = SESSION_RUNNING;
		else {
			Map<String, Long> scoreTable = new HashMap<>();
			persistentAttributes.put("ScoreTable", scoreTable);
			input.getAttributesManager().savePersistentAttributes();
			response = CONFIRMATION;
		}

		return input.getResponseBuilder().withSpeech(response).withShouldEndSession(false).build();
	}

}
=======
package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import edu.hm.skillproject_fr_13.scorekeeper.models.Player;;

public class StartGameSessionIntentHandler implements RequestHandler {
	
	public static final String CONFIRMATION =
			"Die Spielsitzung wurde gestartet. " +
					"<say-as interpret-as=\"interjection\">Viel Glück</say-as>!";
	public static final String SESSION_RUNNING =
			"Es läuft bereits eine Spielsitzung. " +
					"Du must die letzte Spielsitzung beenden, " +
					"bevor du eine neue starten kannst.";
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("StartGameSessionIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Object> persistentAttributes = input.getAttributesManager()
				.getPersistentAttributes();
		final String response;
		
		if (persistentAttributes.containsKey("ActivePlayers"))
			response = SESSION_RUNNING;
		else {
			persistentAttributes
				.put("ActivePlayers", new HashMap<String, Player>());
			input.getAttributesManager().savePersistentAttributes();
			response = CONFIRMATION;
		}

		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}

}
>>>>>>> 8ec73ec20685411b6932cfe2fe1f878bc790b7df
