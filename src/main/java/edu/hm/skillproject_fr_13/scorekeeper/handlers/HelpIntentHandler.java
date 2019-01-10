<<<<<<< HEAD
package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class HelpIntentHandler implements RequestHandler {
	
	public static final String NO_SESSION =
			"Du kannst mich auffordern, eine Spielsitzung zu starten.";
	public static final String IN_SESSION =
			"Nenne mir den Namen eines Spielteilnehmers und seinen Punktestand " +
					"oder frage mich nach dem aktuellen Punktestand.";
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.HelpIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {	
		final boolean isGameSessionRunning = input.getAttributesManager()
				.getPersistentAttributes().containsKey("ScoreTable");
	
		return input.getResponseBuilder()
				.withSpeech(
						"Ich behalte Übersicht über die Punkte in dieser Runde. "
						+ "Spieler können dem Spiel beitreten, sage dazu: SpielerName spielt mit. "
						+ "Danach können die Spieler mit: SpielerName hat X Punkte, Ihre Punkte speichern. "
						+ "Der Befehl: Punktestand ausgeben, kann jederzeit verwendet werden um die Punkte aller Spieler auszugeben. "
						+ "Mit Befehlen wie: Alexa sage Score Keeper SpielerName bekommt X Punkte, können Punkte vergeben werden. "
						)
				.withShouldEndSession(false)
				.build();
				
	}

}
=======
package edu.hm.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class HelpIntentHandler implements RequestHandler {
	
	public static final String NO_SESSION =
			"Du kannst mich auffordern, eine Spielsitzung zu starten.";
	public static final String IN_SESSION =
			"Nenne mir den Namen eines Spielteilnehmers und seinen Punktestand " +
					"oder frage mich nach dem aktuellen Punktestand.";
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.HelpIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {	
		final boolean isGameSessionRunning = input.getAttributesManager()
				.getPersistentAttributes().containsKey("ActivePlayer");
	
		return input.getResponseBuilder()
				.withSpeech(isGameSessionRunning ? IN_SESSION : NO_SESSION)
				.withShouldEndSession(false)
				.build();
				
	}

}
>>>>>>> 8ec73ec20685411b6932cfe2fe1f878bc790b7df
