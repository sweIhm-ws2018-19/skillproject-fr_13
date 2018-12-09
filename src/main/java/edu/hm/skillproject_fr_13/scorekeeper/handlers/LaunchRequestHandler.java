package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import edu.hm.skillproject_fr_13.scorekeeper.models.GameProfile;

public class LaunchRequestHandler implements RequestHandler {
	
	public static final String WELCOME_NEWSESSION =
			"Willkommen im Score Keeper! Du kannst jetzt eine Spielsitzung starten.";
	public static final String WELCOME_CONTINUE =
			"Willkommen zurück!";

	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(LaunchRequest.class));
	}

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Object> persistentAttributes = input.getAttributesManager()
				.getPersistentAttributes();
		final String response;
		
		if (persistentAttributes.containsKey("ActivePlayers"))
			response = WELCOME_CONTINUE;
		else
			response = WELCOME_NEWSESSION;

        persistentAttributes.put("GameProfile", new GameProfile("Default", 0,true, 120, false));
        input.getAttributesManager().savePersistentAttributes();
		
		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}

}
