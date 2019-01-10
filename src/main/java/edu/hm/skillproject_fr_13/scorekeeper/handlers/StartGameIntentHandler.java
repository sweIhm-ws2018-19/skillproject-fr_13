package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import edu.hm.skillproject_fr_13.scorekeeper.models.GameProfile;
import edu.hm.skillproject_fr_13.scorekeeper.models.Player;

public class StartGameIntentHandler implements RequestHandler {
	
	private final String NO_GAME_PROFILE_RESPONSE = "Das Spielprofile wurde noch nicht gesetzt. Bitte Wähle zuerst ein Spielprofil.";
	private final String NO_ACTIVE_PLAYERS = "Es sind nicht genug spieler für das Ausgewählte Spiel vorhanden. Bitte füge dem Spiel zunächst Spieler hinzu.";
	private final String VALIDATE = "Eine neue Spielrunde wurde gestartet.";
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("StartGameIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();

		GameProfile gameProfile = (GameProfile)persistentAttributes.get("GameProfile");
		if (Objects.isNull(gameProfile)) {
			return input.getResponseBuilder().withSpeech(NO_GAME_PROFILE_RESPONSE).build();
		} 
		
		Map<String, Player> playerMap = (Map<String, Player>)(persistentAttributes.get("ActivePlayers"));
		if (Objects.isNull(playerMap) || playerMap.size() < 1 ) {
			return input.getResponseBuilder().withSpeech(NO_ACTIVE_PLAYERS).build();
		}
		
		playerMap.forEach((str, player)-> {player.setPlayerPoints(gameProfile.getUsedGamePointsForGame());});

		input.getAttributesManager().savePersistentAttributes();
		
		return input.getResponseBuilder().withSpeech(VALIDATE).build();
	}

}
