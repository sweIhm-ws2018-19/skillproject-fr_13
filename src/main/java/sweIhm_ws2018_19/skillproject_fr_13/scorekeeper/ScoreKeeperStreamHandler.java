package sweIhm_ws2018_19.skillproject_fr_13.scorekeeper;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import sweIhm_ws2018_19.skillproject_fr_13.scorekeeper.handlers.*;

public class ScoreKeeperStreamHandler extends SkillStreamHandler {
	
    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                       new LaunchRequestHandler(),
                       new SessionEndedRequestHandler(),
                       new CancelAndStopIntentHandler(),
                       new HelpIntentHandler(),
                       new StartGameSessionIntentHandler(),
                       new EndGameSessionIntentHandler(),
                       new GetPlayerScoresIntentHandler(),
                       new SetPlayerScoreIntentHandler())
                .withTableName("scorekeeper-table")
                .withAutoCreateTable(true)
                .build();
    }
    
    public ScoreKeeperStreamHandler() {
        super(getSkill());
    }


}
