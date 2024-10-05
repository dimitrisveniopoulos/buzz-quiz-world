package buzzquizworld.model.round;

import buzzquizworld.model.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

abstract class RoundTest {

    abstract Round getObject();

    @Test
    void setPlayers() {
        Round round = getObject();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Dimitris"));
        players.add(new Player("Giorgos"));
        players.add(new Player("Thanos"));
        players.add(new Player("Christos"));
        round.setPlayers(players);
    }

    @Test
    void getType() {
        Round round = getObject();
        if (round instanceof BettingRound)
            assertEquals(RoundType.BETTING, round.getType());
        else if (round instanceof CorrectAnswerRound)
            assertEquals(RoundType.CORRECT_ANSWER, round.getType());
        else if (round instanceof QuickAnswerRound)
            assertEquals(RoundType.QUICK_ANSWER, round.getType());
        else if (round instanceof StopTheTimerRound)
            assertEquals(RoundType.STOP_THE_TIMER, round.getType());
        else if (round instanceof Thermometer)
            assertEquals(RoundType.THERMOMETER, round.getType());
    }

    @Test
    void getCurrentQuestion() {
        Round round = getObject();
        round.setPlayers(new ArrayList<>());
        round.play();
        if (!(round instanceof Thermometer))
            assertNotNull(round.getCurrentQuestion());
    }

    @Test
    void getQuestionsRemaining() {
        Round round = getObject();
        if (!(round instanceof Thermometer)){
            for (int i = 1; i <= 5; i ++){
                round.setPlayers(new ArrayList<>());
                round.play();
                assertEquals(5 - i, round.getQuestionsRemaining());
            }
        }
    }
}