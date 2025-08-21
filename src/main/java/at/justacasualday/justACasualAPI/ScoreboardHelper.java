package at.justacasualday.justACasualAPI;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

import java.util.List;

public abstract class ScoreboardHelper {

    /**
     * Creates a new Scoreboard
     * @param name Objectives name
     * @param title displayed title
     * @param slot where to Display the Scoreboard
     * @param text text to display
     * @return finished Scoreboard
     */
    public static Scoreboard createScoreboard(String name, String title, DisplaySlot slot, List<String> text) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective(name, Criteria.DUMMY, title);
        objective.setDisplaySlot(slot);

        int scoreNumber = text.size();

        for(String string : text) {
            objective.getScore(string).setScore(scoreNumber);
            scoreNumber--;
        }

        return scoreboard;
    }

    /**
     * Adds a new Objective to an existing Scoreboard
     * @param scoreboard scoreboard to add the Objective
     * @param name Objectives name
     * @param title displayed title
     * @param text text to display
     */
    public static void addObjective(Scoreboard scoreboard, String name, String title, List<String> text) {
        Objective objective = scoreboard.registerNewObjective(name, Criteria.DUMMY, title);

        int scoreNumber = text.size();

        for(String string : text) {
            objective.getScore(string).setScore(scoreNumber);
            scoreNumber--;
        }
    }

    /**
     * Update the text on a given scoreNumber
     * @param scoreboard the scoreboard to update
     * @param slot current active Slot
     * @param scoreNumber number of the text
     * @param text displayed text
     */
    public static void updateLine(Scoreboard scoreboard, DisplaySlot slot, int scoreNumber, String text) {
        Objective objective = scoreboard.getObjective(slot);

        for(String entry : scoreboard.getEntries()) {
            if(objective.getScore(entry).getScore() == scoreNumber) {
                scoreboard.resetScores(entry);
                break;
            }
        }

        objective.getScore(text).setScore(scoreNumber);
    }
}
