package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class SubtractPlayerScoreIntentHandler extends ModifyPlayerScoreIntentHandler {
	
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SubtractPlayerScoreIntent"));
	}
	
	@Override
	public long calculatePoints(String points) {
		return basePoints - Long.parseLong(points);
	}

}
