package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import edu.hm.skillproject_fr_13.scorekeeper.models.Player;

public class GetPlayerScoresIntentHandler implements RequestHandler {
	
	public static final String SCORES = "Der aktuelle Punktestand lautet: %s";
	public static final String NO_SCORES =
			"Ich habe noch keinen Punktestand gespeichert.";

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("GetPlayerScoresIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Player> playerTable = (Map<String, Player>) input
				.getAttributesManager()
				.getPersistentAttributes()
				.get("ActivePlayers");
		final String response;
		
		if (playerTable == null || playerTable.isEmpty())
			response = NO_SCORES;
		else
			response = String.format(SCORES,
					playerTable
					.entrySet()
					.stream()
					.map(entry -> {return "Spieler " + entry.getValue().getName() + ": " + entry.getValue().getPlayerPoints().toString();})
					.collect(Collectors.joining(", ")));
		
		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}

}
