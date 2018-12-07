package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import edu.hm.skillproject_fr_13.scorekeeper.models.GameProfile;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

public abstract class SetGameProfileIntentHandler implements RequestHandler {

    public Optional	<Response> handle(HandlerInput input) {
        GameProfile profile = (GameProfile)input.getAttributesManager().getPersistentAttributes().get("GameProfile");

        ResponseBuilder responseBuilder = input.getResponseBuilder();

        if (profile == null) {
            responseBuilder.withSpeech(SetPositivePlayerScoreIntentHandler.NO_SESSION);
        }
        return responseBuilder
                .withShouldEndSession(false)
                .build();
    }


}