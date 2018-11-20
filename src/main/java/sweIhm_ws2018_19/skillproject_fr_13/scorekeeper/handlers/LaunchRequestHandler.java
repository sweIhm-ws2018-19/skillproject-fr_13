package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.amazon.ask.attributes.persistence.impl.DynamoDbPersistenceAdapter;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

public class LaunchRequestHandler implements RequestHandler {
	
	public static final String WELCOME_NEWSESSION =
			"Willkommen im Score Keeper! Du kannst jetzt eine Spielsitzung starten.";
	public static final String WELCOME_CONTINUE =
			"Willkommen zurück!";

	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(LaunchRequest.class));
	}

	public Optional<Response> handle(HandlerInput input) {
		String response;
		try {
			DynamoDbPersistenceAdapter db = DynamoDbPersistenceAdapter.builder()
					.withTableName("table/lambda-dynamodb-stream")
					.build();
			final Map<String, Object> test = new HashMap<>();
			test.put("ScoreTable", "test");
			
			db.saveAttributes(input.getRequestEnvelope(), test);
			
		final Map<String, Object> persistentAttributes = db.getAttributes(input.getRequestEnvelope()).get();
				
		if (persistentAttributes.containsKey("ScoreTable"))
			response = WELCOME_CONTINUE;
		else
			response = WELCOME_NEWSESSION;
		} catch (Exception e) {
			response = e.getLocalizedMessage() + "; " + Arrays.stream(e.getStackTrace())
					.limit(10)
					.map(StackTraceElement::toString)
					.collect(Collectors.joining("; "));
		}
		
		return input.getResponseBuilder()
				.withSpeech(response)
				.withShouldEndSession(false)
				.build();
	}

}
