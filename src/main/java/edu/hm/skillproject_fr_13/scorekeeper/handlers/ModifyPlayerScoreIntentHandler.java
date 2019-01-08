package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public abstract class ModifyPlayerScoreIntentHandler
		implements SetPlayerScoreIntentHandler {

	protected Long basePoints;

	@Override
	public Optional<Response> handle(HandlerInput input) {

		@SuppressWarnings("unchecked")
		final Map<String, BigDecimal> scoreTable =
				(Map<String, BigDecimal>) input.getAttributesManager()
				.getPersistentAttributes()
				.get("ScoreTable");

		final Map<String, Slot> slots = ((IntentRequest) input.getRequest())
				.getIntent()
				.getSlots();

		try {
			final String playerName = slots.get("PlayerName").getValue();
			basePoints = scoreTable.getOrDefault(
					playerName, BigDecimal.valueOf(0)).longValue();
		} catch (NullPointerException e) {
			basePoints = 0L;
		}

		return SetPlayerScoreIntentHandler.super.handle(input);
	}

}
