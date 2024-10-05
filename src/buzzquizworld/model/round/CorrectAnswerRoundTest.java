package buzzquizworld.model.round;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;
import buzzquizworld.model.question.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CorrectAnswerRoundTest extends RoundTest {

    @Override
    Round getObject() { return new CorrectAnswerRound(RoundType.CORRECT_ANSWER,5,new QuestionHandler()); }

    @Test
    void play() {
        CorrectAnswerRound round = new CorrectAnswerRound(RoundType.CORRECT_ANSWER,5,new QuestionHandler());
        for (int i = 1; i <= 5; i ++){
            round.play();
            assertNotNull(round.getCurrentQuestion());
            assertEquals(5 - i, round.getQuestionsRemaining());
        }
    }

    @Test
    void playerAnswer() {
        CorrectAnswerRound round = new CorrectAnswerRound(RoundType.CORRECT_ANSWER,5,new QuestionHandler());
        Player p1 = new Player("Dimitris");
        float initialScore = p1.getScore();
        round.play();
        Question q = round.currentQuestion;
        int answer = q.getCorrectAnswerIndex();
        assertTrue(round.playerAnswer(p1,answer));
        assertEquals(initialScore + 1000,p1.getScore());
    }

    @Test
    void everyoneHasAnswered() {
        CorrectAnswerRound round = new CorrectAnswerRound(RoundType.CORRECT_ANSWER,5,new QuestionHandler());
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