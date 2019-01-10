<<<<<<< HEAD
package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class SetPositivePlayerScoreIntentHandler implements SetPlayerScoreIntentHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SetPositivePlayerScoreIntent"));
	}
	
	@Override
	public long calculatePoints(String points) {
		return Long.parseLong(points);
	}

}
=======
package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class SetPositivePlayerScoreIntentHandler extends SetPlayerScoreIntentHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SetPositivePlayerScoreIntent"));
	}
	
	@Override
	protected Long parsePoints(String points) {
		return Long.parseLong(points);
	}

}
>>>>>>> 8ec73ec20685411b6932cfe2fe1f878bc790b7df
