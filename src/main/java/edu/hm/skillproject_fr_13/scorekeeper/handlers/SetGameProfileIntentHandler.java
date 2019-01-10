package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import edu.hm.skillproject_fr_13.scorekeeper.models.GameProfile;

public class SetGameProfileIntentHandler implements RequestHandler {
	

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SetGameProfileIntentHandler"));
	}

    public Optional	<Response> handle(HandlerInput input) {
        Map<String, Object> attributes = input.getAttributesManager().getPersistentAttributes();
        
        attributes.put("GameProfile", new GameProfile("Default", 0,true, 120, false));
        
        input.getAttributesManager().savePersistentAttributes();
        
        return input.getResponseBuilder().withSpeech("Das Standart Spielprofil wurde ausgewählt.").build();
    }
}