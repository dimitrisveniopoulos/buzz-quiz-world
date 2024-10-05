package buzzquizworld.model.round;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;
import buzzquizworld.model.question.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuickAnswerRoundTest extends RoundTest {

    @Override
    Round getObject() { return new QuickAnswerRound(RoundType.QUICK_ANSWER,5,new QuestionHandler()); }

    @Test
    void play() {
        QuickAnswerRound round = new QuickAnswerRound(RoundType.QUICK_ANSWER,5,new QuestionHandler());
        for (int i = 1; i <= 5; i ++){
            round.play();
            assertNotNull(round.getCurrentQuestion());
            assertEquals(5 - i, round.getQuestionsRemaining());
        }
    }

    @Test
    void playerAnswer() {
        QuickAnswerRound round = new QuickAnswerRound(RoundType.QUICK_ANSWER,5,new QuestionHandler());
        round.play();
        Question q = round.currentQuestion;

        Player p1 = new Player("Dimitris");
        float initialScore1 = p1.getScore();
        int answer1 = q.getCorrectAnswerIndex();
        assertTrue(round.playerAnswer(p1,answer1));
        assertEquals(initialScore1 + 1000,p1.getScore());


        Player p2 = new Player("Chris");
        float initialScore2 = p2.getScore();
        int answer2 = q.getCorrectAnswerIndex();
        assertTrue(round.playerAnswer(p2,answer2));
        assertEquals(initialScore1 + 500,p2.getScore());
    }

    @Test
    void everyoneHasAnswered() {
        QuickAnswerRound round = new QuickAnswerRound(RoundType.QUICK_ANSWER,5,new QuestionHandler());
        round.play();
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Dimitris");
        Player p2 = new Player("Chris");
        players.add(p1);
        players.add(p2);
        round.setPlayers(players);
        assertFalse(round.everyoneHasAnswered());
        round.playerAnswer(p1,1);
        assertFalse(round.everyoneHasAnswered());
        round.playerAnswer(p2,3);
        assertTrue(round.everyoneHasAnswered());
    }
}