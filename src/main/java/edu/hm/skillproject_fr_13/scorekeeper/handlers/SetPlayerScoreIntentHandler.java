package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

interface SetPlayerScoreIntentHandler extends RequestHandler {

	public static final String CONFIRMATION = "%d Punkte für %s gespeichert.";
	public static final String NO_SESSION = "Du musst zuerst eine Spielsitzung starten.";
	public static final String PLAYER_NOT_FOUND = "Der Spieler wurde nicht gefunden.";
	public static final String REPROMPT = "Ähm... was?";

	@Override
	default Optional<Response> handle(HandlerInput input) {
		@SuppressWarnings("unchecked")
		final Map<String, Long> scoreTable = (Map<String, Long>) input
				.getAttributesManager()
				.getPersistentAttributes()
				.get("ScoreTable");
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (scoreTable == null)
			responseBuilder.withSpeech(NO_SESSION);
		else
			try {
				final Map<String, Slot> slots = ((IntentRequest) input.getRequest())
						.getIntent()
						.getSlots();

				final String playerName = slots.get("PlayerName").getValue();
				final Long points = parsePoints(slots.get("Points").getValue());

				scoreTable.put(playerName, points);
				input.getAttributesManager().savePersistentAttributes();

				responseBuilder
					.withSpeech(String.format(CONFIRMATION, points, playerName));

			} catch (NullPointerException | NumberFormatException e) {
				responseBuilder.withReprompt(REPROMPT);
			}

		return responseBuilder
				.withShouldEndSession(false)
				.build();
	}
	
	long parsePoints(String points);

}
