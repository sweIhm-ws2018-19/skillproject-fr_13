package edu.hm.skillproject_fr_13.scorekeeper;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

<<<<<<< HEAD
import edu.hm.skillproject_fr_13.scorekeeper.handlers.AddPlayerIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.DeletePlayerIntentHandler;
=======
import edu.hm.skillproject_fr_13.scorekeeper.handlers.AddPlayerHandler;
>>>>>>> 8ec73ec20685411b6932cfe2fe1f878bc790b7df
import edu.hm.skillproject_fr_13.scorekeeper.handlers.CancelAndStopIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.DeletePlayerHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.EndGameSessionIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.GetPlayerScoresIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.HelpIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.LaunchRequestHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.SessionEndedRequestHandler;
<<<<<<< HEAD
import edu.hm.skillproject_fr_13.scorekeeper.handlers.SetBackIntentHandler;
=======
import edu.hm.skillproject_fr_13.scorekeeper.handlers.SetGameProfileIntentHandler;
>>>>>>> 8ec73ec20685411b6932cfe2fe1f878bc790b7df
import edu.hm.skillproject_fr_13.scorekeeper.handlers.SetNegativePlayerScoreIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.SetPositivePlayerScoreIntentHandler;
import edu.hm.skillproject_fr_13.scorekeeper.handlers.StartGameIntentHandler;
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
                       new AddPlayerHandler(),
                       new DeletePlayerHandler(),
                       new StartGameSessionIntentHandler(),
                       new EndGameSessionIntentHandler(),
                       new SetGameProfileIntentHandler(),
                       new StartGameIntentHandler(),
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
