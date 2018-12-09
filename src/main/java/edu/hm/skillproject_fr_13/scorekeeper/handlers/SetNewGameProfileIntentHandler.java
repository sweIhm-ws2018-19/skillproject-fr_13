package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import edu.hm.skillproject_fr_13.scorekeeper.interfaces.GamePoints;
import edu.hm.skillproject_fr_13.scorekeeper.models.GameProfile;
import edu.hm.skillproject_fr_13.scorekeeper.models.SingleGamePoints;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

public abstract class SetNewGameProfileIntentHandler implements RequestHandler {

    private final String TEMP_PROFILE_NAME = "temp";
    private final String START_POINTS_SLOT = "StartPoints";
    private final String CREATED_TEMP_PROFILE = "Ein Profil mit dem Startwert %i angelegt.";


    public Optional<Response> handle(HandlerInput input) {
        GameProfile profile = (GameProfile)input.getAttributesManager().getPersistentAttributes().get("GameProfile");

        ResponseBuilder responseBuilder = input.getResponseBuilder();

        Slot startPointsSlot = ((IntentRequest)input.getRequest())
                                .getIntent()
                                .getSlots()
                                .get(START_POINTS_SLOT);

        GamePoints startPoints = new SingleGamePoints(Integer.parseInt(startPointsSlot.getValue()));

        profile = new GameProfile(TEMP_PROFILE_NAME, startPoints, false, false);
        
        return responseBuilder
                .withSpeech(String.format(CREATED_TEMP_PROFILE, startPoints.getPointsByName(SingleGamePoints.NAME)))
                .withShouldEndSession(false)
                .build();
    }


}