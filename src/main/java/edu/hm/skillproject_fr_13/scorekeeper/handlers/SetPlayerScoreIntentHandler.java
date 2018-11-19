package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

public class SetPlayerScoreIntentHandler implements RequestHandler {
	
	public static final String CONFIRMATION = "Spieler %s hat %d Punkte.";
	public static final String REPROMPT = "Ähm... was?";
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SetPlayerScoreIntentHandler")) &&
				input.getAttributesManager().getSessionAttributes().containsKey("ScoreTable");
	}

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Slot> slots = ((IntentRequest) input.getRequest()).getIntent().getSlots();
		final Slot playerNameSlot = slots.get("PlayerName");
		final Slot pointsSlot = slots.get("Points");
		final ResponseBuilder responseBuilder = input.getResponseBuilder();
		
		try {
			final String playerName = playerNameSlot.getValue();
			final long points = Long.parseLong(pointsSlot.getValue());
			
			((Map<String, Long>) input.getAttributesManager()
				.getSessionAttributes()
				.get("ScoreTable"))
				.put(playerName, points);
			
			responseBuilder.withSpeech(String.format(CONFIRMATION, playerName, points));
			
			
		} catch (NullPointerException | NumberFormatException e) {
			responseBuilder.withReprompt(REPROMPT);
		}
		
		return responseBuilder.withShouldEndSession(false).build();
	}

}
