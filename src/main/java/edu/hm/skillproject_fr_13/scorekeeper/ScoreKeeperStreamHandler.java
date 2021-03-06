package edu.hm.skillproject_fr_13.scorekeeper;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import edu.hm.skillproject_fr_13.scorekeeper.handlers.AddPlayerIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.DeletePlayerIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.CancelAndStopIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.EndGameSessionIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.GetPlayerScoresIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.HelpIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.LaunchRequestHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.SessionEndedRequestHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.SetBackIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.SetNegativePlayerScoreIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.SetPositivePlayerScoreIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.StartGameSessionIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.AddPlayerScoreIntentHandler;import edu.hm.skillproject_fr_13.scorekeeper.handlers.SubtractPlayerScoreIntentHandler;

public class ScoreKeeperStreamHandler extends SkillStreamHandler {
	
    @SuppressWarnings("unchecked")
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
                       new SetNegativePlayerScoreIntentHandler(),
                       new AddPlayerIntentHandler(),
                       new DeletePlayerIntentHandler(),
                       new AddPlayerScoreIntentHandler(),
                       new SubtractPlayerScoreIntentHandler(),
                       new SetBackIntentHandler()	)
                .withTableName("scorekeeper-table")
                .withAutoCreateTable(true)
                .build();
    }
    
    public ScoreKeeperStreamHandler() {
        super(getSkill());
    }

}
