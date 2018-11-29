package com.edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class SetNegativePlayerScoreIntentHandler extends SetPlayerScoreIntentHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SetNegativePlayerScoreIntent"));
	}
	
	@Override
	protected Long parsePoints(String points) {
		return -Long.parseLong(points);
	}

}
