package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import edu.hm.skillproject_fr_13.scorekeeper.models.Player;

public class AddPlayerHandler implements RequestHandler {
	
	private final String SUCCESSFUL = "Der neue Spieler ";
	private final String UNSUCCESSFUL = "Der Spieler konnte nicht hinzugefügt werden, sein Name ist schon vergeben.";
	
	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AddPlayerIntent"));
	}
	
	@Override
	public Optional<Response> handle(HandlerInput input) {
		Map<String, Player> playerBase = (Map<String, Player>) input
				.getAttributesManager()
				.getPersistentAttributes()
				.get("ActivePlayers");
		
		if(Objects.isNull(playerBase)) {
			playerBase = new HashMap<String, Player>();
			input.getAttributesManager()
			.getPersistentAttributes()
			.put("ActivePlayers", playerBase);
		}
		
		final Map<String, Slot> slots = ((IntentRequest) input.getRequest())
						.getIntent()
						.getSlots();
		final String nameOfPlayerToAdd = slots.get("PlayerToAdd").getValue();
		
		if(playerBase.containsKey(nameOfPlayerToAdd)) {
			return input.getResponseBuilder().withSpeech(UNSUCCESSFUL).build();
		} else {
			playerBase.put(nameOfPlayerToAdd, new Player(nameOfPlayerToAdd));
			input.getAttributesManager().savePersistentAttributes();
			return input.getResponseBuilder().withSpeech(SUCCESSFUL + nameOfPlayerToAdd + " wurde zum Spielen hinzugefügt.").build();
		}
	}
}
