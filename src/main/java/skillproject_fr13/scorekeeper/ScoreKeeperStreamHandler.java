package skillproject_fr13.scorekeeper;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import skillproject_fr13.scorekeeper.handlers.CancelAndStopIntentHandler;
import skillproject_fr13.scorekeeper.handlers.EndGameSessionIntentHandler;
import skillproject_fr13.scorekeeper.handlers.GetPlayerScoresIntentHandler;
import skillproject_fr13.scorekeeper.handlers.HelpIntentHandler;
import skillproject_fr13.scorekeeper.handlers.LaunchRequestHandler;
import skillproject_fr13.scorekeeper.handlers.SessionEndedRequestHandler;
import skillproject_fr13.scorekeeper.handlers.SetNegativePlayerScoreIntentHandler;
import skillproject_fr13.scorekeeper.handlers.SetPositivePlayerScoreIntentHandler;
import skillproject_fr13.scorekeeper.handlers.StartGameSessionIntentHandler;

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
                       new SetPositivePlayerScoreIntentHandler(),
                       new SetNegativePlayerScoreIntentHandler())
                .withTableName("scorekeeper-table")
                .withAutoCreateTable(true)
                .build();
    }
    
    public ScoreKeeperStreamHandler() {
        super(getSkill());
    }

}
