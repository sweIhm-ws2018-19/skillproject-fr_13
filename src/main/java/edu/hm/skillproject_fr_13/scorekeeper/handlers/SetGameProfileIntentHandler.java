package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class SetGameProfileIntentHandler implements RequestHandler {
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SetGameProfileIntentHandler"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		final Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();

		try {
			final Map<String, String> gameProfile = new HashMap<String, String>();
			gameProfile.put("MinPlayerCount", "1");
			gameProfile.put("MaxPlayerCount", "4");
			gameProfile.put("PointStartValue", "0");
			gameProfile.put("PointEndValue", "100");
			gameProfile.put("Name", "Default");
			persistentAttributes.put("GameProfile", gameProfile);
			input.getAttributesManager().savePersistentAttributes();
		} catch (Exception ex) {
			return input.getResponseBuilder().withSpeech("Du kannst keine komplexen Objekte speichern.").build();
		}

		return input.getResponseBuilder().withSpeech("Standart Spiel wurde ausgewählt").withShouldEndSession(false)
				.build();
	}
}
