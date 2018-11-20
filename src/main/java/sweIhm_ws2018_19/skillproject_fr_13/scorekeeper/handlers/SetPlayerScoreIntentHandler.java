package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public class SetPlayerScoreIntentHandler implements RequestHandler {
	
	public static final String CONFIRMATION = "Spieler %s hat %d Punkte.";
	public static final String REPROMPT = "Ähm... was?";
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SetPlayerScoreIntent")) &&
				input.getAttributesManager()
					.getPersistentAttributes()
					.containsKey("ScoreTable");
	}

	public Optional<Response> handle(HandlerInput input) {
		final Map<String, Slot> slots = ((IntentRequest) input.getRequest())
				.getIntent()
				.getSlots();
		final Slot playerNameSlot = slots.get("PlayerName");
		final Slot pointsSlot = slots.get("Points");
		String response;
		
		try {
			final String playerName = playerNameSlot.getValue();
			final long points = Long.parseLong(pointsSlot.getValue());
			
			final Map<String, Object> persistentAttributes = input
					.getAttributesManager()
					.getPersistentAttributes();
			
			final Map<String, Long> scoreTable = (Map<String, Long>) persistentAttributes
					.get("ScoreTable");
			
			scoreTable.put(playerName, points);
			persistentAttributes.put("ScoreTable", scoreTable);
			input.getAttributesManager().savePersistentAttributes();
			
			response = String.format(CONFIRMATION, playerName, points);
			
		} catch (NullPointerException | NumberFormatException e) {
			response = REPROMPT;
		}
		
		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}

}
