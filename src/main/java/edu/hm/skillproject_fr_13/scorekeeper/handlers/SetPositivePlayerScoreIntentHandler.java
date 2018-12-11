package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class SetPositivePlayerScoreIntentHandler implements SetPlayerScoreIntentHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SetPositivePlayerScoreIntent"));
	}
	
	@Override
	public long parsePoints(String points) {
		return Long.parseLong(points);
	}

}
