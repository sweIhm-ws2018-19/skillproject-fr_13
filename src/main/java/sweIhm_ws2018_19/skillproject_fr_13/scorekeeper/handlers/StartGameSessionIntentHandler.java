package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.HashMap;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class StartGameSessionIntentHandler implements RequestHandler {
	
	public static final String CONFIRMATION =
			"Die Spielsitzung wurde gestartet." +
					"<say-as interpret-as=\"interjection\">viel glück</say-as>!";
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("StartGameSessionIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		input.getAttributesManager()
			.getSessionAttributes()
			.put("ScoreTable", new HashMap<String, Long>());
	
		return input.getResponseBuilder()
				.withSpeech(CONFIRMATION)
				.build();
	}

}
