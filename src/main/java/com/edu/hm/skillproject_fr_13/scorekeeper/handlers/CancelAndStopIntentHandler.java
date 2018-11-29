package com.edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class CancelAndStopIntentHandler implements RequestHandler {
	
	public static final String BYE = "Bis zum nächsten Mal!";
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.CancelIntent").or(intentName("AMAZON.StopIntent")));
	}

	public Optional<Response> handle(HandlerInput input) {
		return input.getResponseBuilder()
				.withSpeech(BYE)
				.withShouldEndSession(true)
				.build();
	}

}
