package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

public class AddPlayerIntentHandler implements RequestHandler {
	
	public static final String NO_SESSION = "Du musst zuerst eine Spielsitzung starten.";
	public static final long DEFAULT_POINT_VALUE = 0l;
	
	public static final UnaryOperator<String> PLAYER_CONTAINED = playerName -> "Der Spieler " + playerName + " spielt bereits mit.";
	public static final UnaryOperator<String> CONFIRMATION = playerName -> "Der Spieler: " + playerName + " nimmt nun am Spiel teil.";
	
	
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AddPlayerIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		
		final Map<String, Long> scoreTable = (Map<String,Long>) input.getAttributesManager()
				.getPersistentAttributes().get("ScoreTable");
		final ResponseBuilder responseBuilder = input.getResponseBuilder();
		

		if (scoreTable == null)
			responseBuilder.withSpeech(NO_SESSION);
		else {
			final Map<String, Slot> slots = ((IntentRequest) input.getRequest()).getIntent().getSlots();
			final String playerName = slots.get("PlayerName").getValue();

			if(scoreTable.containsKey(playerName)) {
				responseBuilder.withSpeech(PLAYER_CONTAINED.apply(playerName));
			} else {
				scoreTable.put(playerName, DEFAULT_POINT_VALUE);
				input.getAttributesManager().savePersistentAttributes();
				responseBuilder.withSpeech(CONFIRMATION.apply(playerName));
			}
		}
		return responseBuilder.withShouldEndSession(false).build();
	}
}
