package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class GetPlayerScoresIntentHandler implements RequestHandler {
	
	public static final String SCORES = "Der aktuelle Punktestand lautet: %s";
	public static final String NO_SCORES =
			"Ich habe noch keinen Punktestand gespeichert.";

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("GetPlayerScoresIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Long> scoreTable = (Map<String, Long>) input
				.getAttributesManager()
				.getPersistentAttributes()
				.get("ScoreTable");
		final String response;
		
		if (scoreTable == null || scoreTable.isEmpty())
			response = NO_SCORES;
		else
			response = String.format(SCORES,
					scoreTable
					.entrySet()
					.stream()
					.map(entry -> "Spieler " + entry.getKey() + ": " +
							entry.getValue() + " Punkte")
					.collect(Collectors.joining(", ")));
		
		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}

}