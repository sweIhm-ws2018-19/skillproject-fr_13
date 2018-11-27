package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public abstract class SetPlayerScoreIntentHandler implements RequestHandler {

	public static final String CONFIRMATION = "%d Punkte für %s gespeichert.";
	public static final String NO_SESSION = "Du musst zuerst eine Spielsitzung starten.";
	public static final String REPROMPT = "Ähm... was?";

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Long> scoreTable = (Map<String, Long>) input
				.getAttributesManager()
				.getPersistentAttributes()
				.get("ScoreTable");
		String response;

		if (scoreTable == null)
			response = NO_SESSION;
		else
			try {
				final Map<String, Slot> slots = ((IntentRequest) input.getRequest())
						.getIntent()
						.getSlots();

				final String playerName = slots.get("PlayerName").getValue();
				final long points = parsePoints(slots.get("Points").getValue());

				scoreTable.put(playerName, points);
				input.getAttributesManager().savePersistentAttributes();

				response = String.format(CONFIRMATION, points, playerName);

			} catch (NullPointerException | NumberFormatException e) {
				response = REPROMPT;
			}

		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}

	protected abstract Long parsePoints(String points);

}
