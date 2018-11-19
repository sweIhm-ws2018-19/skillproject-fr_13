package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers.CancelAndStopIntentHandler;
import sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers.HelpIntentHandler;
import sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers.LaunchRequestHandler;
import sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers.SessionEndedRequestHandler;
import sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers.StartGameSessionIntentHandler;

public class ScoreKeeperStreamHandler extends SkillStreamHandler {
	
    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                       new CancelAndStopIntentHandler(),
                       new StartGameSessionIntentHandler(),
                       new HelpIntentHandler(),
                       new LaunchRequestHandler(),
                       new SessionEndedRequestHandler())
                .build();
    }
    
    public ScoreKeeperStreamHandler() {
        super(getSkill());
    }


}
