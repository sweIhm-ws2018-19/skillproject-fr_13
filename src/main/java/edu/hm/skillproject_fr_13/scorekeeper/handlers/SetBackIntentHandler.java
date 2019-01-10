package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

public class SetBackIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SetBackIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		@SuppressWarnings("unchecked")
		final Map<String, Long> scoreTable = (Map<String, Long>) input.getAttributesManager().getPersistentAttributes()
				.get("ScoreTable");
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (scoreTable == null) {
			responseBuilder.withSpeech(SetPlayerScoreIntentHandler.NO_SESSION);
		} else {
			for (String player : scoreTable.keySet())	 {
				scoreTable.put(player, Long.valueOf(0l));
			}
		}
		input.getAttributesManager().savePersistentAttributes();
		return responseBuilder.withShouldEndSession(true).build();
	}

}
