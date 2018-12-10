package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

public abstract class SetPlayerScoreIntentHandler implements RequestHandler {

	public static final String CONFIRMATION = "%d Punkte für %s gespeichert.";
	public static final String NO_SESSION = "Du musst zuerst eine Spielsitzung starten.";
	public static final String REPROMPT = "Ähm... was?";

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Map<String, Long>> ActivePlayers = (Map<String, Map<String,Long>>) input.getAttributesManager()
				.getPersistentAttributes().get("ActivePlayers");
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (ActivePlayers == null)
			responseBuilder.withSpeech(NO_SESSION);
		else
			try {
				final Map<String, Slot> slots = ((IntentRequest) input.getRequest()).getIntent().getSlots();

				final String playerName = slots.get("PlayerName").getValue();
				final long points = parsePoints(slots.get("Points").getValue());

				if (ActivePlayers.containsKey(playerName)) {
					ActivePlayers.get(playerName).put("default", points);
					responseBuilder.withSpeech(String.format(CONFIRMATION, points, playerName));
					input.getAttributesManager().savePersistentAttributes();
				} else
					responseBuilder.withSpeech("Der Spieler wurde nicht gefunden.");
			} catch (NullPointerException | NumberFormatException e) {
				responseBuilder.withReprompt(REPROMPT);
			}

		return responseBuilder.withShouldEndSession(false).build();
	}

	protected abstract Long parsePoints(String points);

}
