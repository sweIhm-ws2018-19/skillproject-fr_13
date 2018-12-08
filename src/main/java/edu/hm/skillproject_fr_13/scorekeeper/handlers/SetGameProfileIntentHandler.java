package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.hm.skillproject_fr_13.scorekeeper.models.GameProfile;

public class SetGameProfileIntentHandler implements RequestHandler {

    public static final String NO_SUCH_PROFILE = "Das gewünschte Profil %s konnte nicht gefunden werden.";
    public static final String PROFILE_SET = "Das Profil %s wurde geladen.";
    public static final String PROFILE_ATTRIBUTE_NAME = "GameProfile";
    public static final String PATH_TO_GAME_PROFILES = "gameProfiles.json";

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SetGameProfileIntentHandler"));
    }

    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();

        ResponseBuilder responseBuilder = input.getResponseBuilder();

        Map<String, Slot> slots = ((IntentRequest)input.getRequest())
                                                        .getIntent()
                                                        .getSlots();

        String profileName = slots.get(PROFILE_ATTRIBUTE_NAME).getValue();
        Optional<GameProfile> optionalProfile = Optional.empty();
        String speechResponse;

        try {
            optionalProfile = getGameProfileByName(getGameProfiles(), profileName);
        } catch (IOException ioe) {
            speechResponse = String.format(NO_SUCH_PROFILE, profileName);
        }

        if (optionalProfile.isPresent()) {
            persistentAttributes.put(SetGameProfileIntentHandler.PROFILE_ATTRIBUTE_NAME, optionalProfile.get());
            speechResponse = String.format(PROFILE_SET, profileName);
        } else {
            speechResponse = String.format(NO_SUCH_PROFILE, profileName);
        }
        
        
        return responseBuilder
                .withSpeech(speechResponse)
                .withShouldEndSession(false)
                .build();
    }


    public List<GameProfile> getGameProfiles() throws IOException{
        List<GameProfile> gameProfiles = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        gameProfiles = mapper.readValue(PATH_TO_GAME_PROFILES, List.class);

        return gameProfiles;
    }

    public Optional<GameProfile> getGameProfileByName(List<GameProfile> gameProfiles, String name) { 
        for(GameProfile profile: gameProfiles) {
            if (profile.getName().equals(name)) {
                return Optional.of(profile);
            }
        }

        return Optional.empty();
    }

}