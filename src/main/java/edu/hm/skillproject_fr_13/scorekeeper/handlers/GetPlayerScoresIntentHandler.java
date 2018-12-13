package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class GetPlayerScoresIntentHandler implements RequestHandler {

	public static final String SCORES = "Der aktuelle Punktestand lautet: ";
	public static final String NO_SCORES =
			"Ich habe noch keinen Punktestand gespeichert.";

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("GetPlayerScoresIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		@SuppressWarnings("unchecked")
		final Map<String, BigDecimal> scoreTable =
				(Map<String, BigDecimal>) input.getAttributesManager()
				.getPersistentAttributes()
				.get("ScoreTable");
		final String response;

		if (scoreTable == null || scoreTable.isEmpty())
			response = NO_SCORES;
		else
			response = SCORES + scoreTable.entrySet().stream()
			.map(entry -> "Spieler: " + entry.getKey() + " hat: "
							+ entry.getValue() + " Punkte. ")
			.collect(Collectors.joining(", "));

		return input.getResponseBuilder().withSpeech(response)
				.withShouldEndSession(false).build();
	}
}
