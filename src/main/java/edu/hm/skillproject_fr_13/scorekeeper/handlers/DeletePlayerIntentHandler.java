package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static com.amazon.ask.request.Predicates.intentName;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

public class DeletePlayerIntentHandler implements RequestHandler {

	public static final String NO_SESSION =
			"Es sind momentan bereits keine Spieler vorhanden.";
	public static final Function<String, String> PLAYER_NOT_CONTAINED =
			playerName -> "Der Spieler " + playerName 
			+ " ist mir nicht bekannt.";
	public static final Function<String, String> PLAYER_DELETED =
			playerName -> "Der Spieler: " + playerName
			+ " wurde von Spiel entfernt.";

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("DeletePlayerIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		@SuppressWarnings("unchecked")
		final Map<String, Long> scoreTable =
				(Map<String, Long>) input.getAttributesManager()
				.getPersistentAttributes()
				.get("ScoreTable");
		final ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (scoreTable == null || scoreTable.size() == 0)
			responseBuilder.withSpeech(NO_SESSION);
		else {
			final Map<String, Slot> slots =
					((IntentRequest) input.getRequest())
					.getIntent()
					.getSlots();
			final String playerName = slots.get("PlayerName").getValue();

			if (scoreTable.containsKey(playerName)) {
				scoreTable.remove(playerName);
				input.getAttributesManager().savePersistentAttributes();
				responseBuilder.withSpeech(PLAYER_DELETED.apply(playerName));
			} else {
				responseBuilder.withSpeech(
						PLAYER_NOT_CONTAINED.apply(playerName));
			}
		}

		return responseBuilder.withShouldEndSession(false).build();
	}
}
