package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;


public class GetSpecificPlayerScoreIntentHandler implements RequestHandler {
	
	public static final String SCORES = "Der gewünschte Punktestand lautet: ";
	public static final String NO_SCORES =
			"Ich habe noch keinen Punktestand gespeichert.";

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("GetSpecificPlayerScoreIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Long> scoreTable = (Map<String, Long>) input
				.getAttributesManager()
				.getPersistentAttributes()
				.get("ScoreTable");
		
		final String response;
		
		if (scoreTable == null || scoreTable.isEmpty())
			response = NO_SCORES;
		else {
				final Map<String, Slot> slots = ((IntentRequest) input.getRequest())
						.getIntent()
						.getSlots();

				final String playerName = slots.get("PlayerName").getValue();
				
				response = String.format(SCORES,
						scoreTable
						.entrySet()
						.stream()
						.map(entry -> playerName + entry.getKey() + ": " +
								scoreTable.get(playerName) + " Punkte"));
			

		}
		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}
}
