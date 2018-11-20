package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class GetPlayerScoresIntentHandler implements RequestHandler {
		
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("GetPlayerScoresIntent")) &&
				input.getAttributesManager().getSessionAttributes().containsKey("ScoreTable");
	}

	public Optional<Response> handle(HandlerInput input) {
		final Map <String, Object> persistentAttributes = input.getAttributesManager()
				.getPersistentAttributes();
		return input.getResponseBuilder()
				.withSpeech("Der aktuelle Punktestand lautet:" +
						((Map<String, Long>) persistentAttributes
							.get("ScoreTable"))
							.entrySet()
							.stream()
							.map(entry ->
								"Spieler" + entry.getKey() + ": " + entry.getValue() + "Punkte;")
							.collect(Collectors.joining(" ")))
				.withShouldEndSession(false)
				.build();
	}

}
