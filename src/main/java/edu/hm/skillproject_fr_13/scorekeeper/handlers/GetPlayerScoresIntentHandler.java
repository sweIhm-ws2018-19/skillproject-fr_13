package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class GetPlayerScoresIntentHandler implements RequestHandler {

	public static final String SCORES = "Der aktuelle Punktestand lautet: ";
	public static final String NO_SCORES = "Ich habe noch keinen Punktestand gespeichert.";

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("GetPlayerScoresIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Map<String, Long>> ActivePlayers = (Map<String, Map<String, Long>>) input
				.getAttributesManager()
				.getPersistentAttributes()
				.get("ActivePlayers");
		final String response;
		
		if (ActivePlayers == null || ActivePlayers.isEmpty())
			response = NO_SCORES;
		else
			response = SCORES +  
					ActivePlayers
					.entrySet()
					.stream()
					.map(entry -> {return entry.getKey() + ": " + 
							entry
							.getValue()
							.entrySet()
							.stream()
							.map(points -> 
								points.getKey().equals("default")?
										points.getValue() + " Punkte":
										entry.getKey() + ": " + entry.getValue() )
							.collect(Collectors.joining(", "));
					})
					.collect(Collectors.joining(", "));
					
//					ActivePlayers
//					.entrySet()
//					.stream()
//					.map(entry -> "Spieler " + entry.getKey() + ": " + 
//							entry
//							.getValue()
//							.entrySet()
//							.stream()
//							.map(str -> 
//								{return str.equals("default")?"" + entry.getValue() + " Punkte":str + ", " + getValue();})
//							.collect(Collectors.joining(", ")))
//					.collect(Collectors.joining(", ")));
					
		
		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}
}
