package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

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
		
		if (persistentAttributes.containsKey("GameProfile"))
			response = SESSION_RUNNING;
		else {
			Map<String, Map<String, Long>> map = new HashMap<String, Map<String,Long>>();
			Map<String, Long> points = new HashMap<String, Long>();
			points.put("default", 10l);
			map.put("Alex", points);
			persistentAttributes
				.put("ActivePlayers", map);
			input.getAttributesManager().savePersistentAttributes();
			response = CONFIRMATION;
		}

		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}

}
