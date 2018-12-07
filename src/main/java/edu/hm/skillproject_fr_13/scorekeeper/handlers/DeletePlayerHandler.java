package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import edu.hm.skillproject_fr_13.scorekeeper.models.Player;

public class DeletePlayerHandler implements RequestHandler {

	final String ACTIVE_PLAYERS_NOT_EXIST = "Es wurden noch keine Spieler erstellt, "
			+ "daher können auch keine Spieler gelöscht werden" 
			+ ", bitte füge zunächst deinem Spiel Spieler hinzu.";
	final String NO_PLAYERS_ACTIVE = "Momentan sind keine Spieler aktive daher können auch keine gelöscht werden.";
	final String SUCCESSFUL = "Spieler wurde gelöscht.";
	final String UNSUCCESSFUL = "Spieler wurde nicht gelöscht, er wurde nicht aufgefunden.";

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("DeletePlayerIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		Map<String, Player> playerBase = (Map<String, Player>) input
				.getAttributesManager()
				.getPersistentAttributes()
				.get("ActivePlayers");
		
		if(Objects.isNull(playerBase)) {
			return input.getResponseBuilder().withSpeech(ACTIVE_PLAYERS_NOT_EXIST).build();
		}
		if (playerBase.isEmpty()) {
			return input.getResponseBuilder().withSpeech(NO_PLAYERS_ACTIVE).build();	
		}
		
		final Map<String, Slot> slots = ((IntentRequest) input.getRequest())
						.getIntent()
						.getSlots();
		final String nameOfPlayerToRemove = slots.get("PlayerToRemove").getValue();
		
		if(playerBase.containsKey(nameOfPlayerToRemove)) {
			playerBase.remove(nameOfPlayerToRemove);
			input.getAttributesManager()
			.getPersistentAttributes()
			.put("ActivePlayers", playerBase);
			
			input.getAttributesManager().savePersistentAttributes();
			
			return input.getResponseBuilder()
					.withSpeech(SUCCESSFUL).build();
		} else {
			return input.getResponseBuilder()
					.withSpeech(UNSUCCESSFUL).build();
		}
	}
}
