package buzzquizworld.model.round;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;
import buzzquizworld.model.question.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BettingRoundTest extends RoundTest{

    @Override
    Round getObject() { return new BettingRound(RoundType.BETTING, 5, new QuestionHandler()); }

    @Test
    void play() {
        BettingRound round = new BettingRound(RoundType.BETTING, 5, new QuestionHandler());
        for (int i = 1; i <= 5; i ++){
            round.play();
            assertNotNull(round.getCurrentQuestion());
            assertEquals(5 - i, round.getQuestionsRemaining());
        }
    }

    @Test
    void playerAnswer() {
        BettingRound round = new BettingRound(RoundType.BETTING, 5, new QuestionHandler());
        Player p1 = new Player("Dimitris");
        float initialScore = p1.getScore();
        round.play();
        Question q = round.currentQuestion;
        int answer = q.getCorrectAnswerIndex();
        assertTrue(round.playerAnswer(p1,answer,1000));
        assertEquals(initialScore + 1000,p1.getScore());
    }

    @Test
    void everyoneHasAnswered() {
        BettingRound round = new BettingRound(RoundType.BETTING, 5, new QuestionHandler());
        round.play();
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Dimitris");
        Player p2 = new Player("Chris");
        players.add(p1);
        players.add(p2);
        round.setPlayers(players);
        assertFalse(round.everyoneHasAnswered());
        round.playerAnswer(p1,1,1000);
        assertFalse(round.everyoneHasAnswered());
        round.playerAnswer(p2,1,1000);
        assertTrue(round.everyoneHasAnswered());
    }

    @Test
    void setBet() {
        BettingRound round = new BettingRound(RoundType.BETTING, 5, new QuestionHandler());
        round.play();
        Player p1 = new Player("Dimitris");
        int bet = 363;
        round.setBet(p1, bet);
        assertEquals(bet,round.getBet(p1));
    }

    @Test
    void getBet() {
        BettingRound round = new BettingRound(RoundType.BETTING, 5, new QuestionHandler());
        round.play();
        Player p1 = new Player("Dimitris");
        int bet = 1000;
        round.setBet(p1, bet);
        assertEquals(bet,round.getBet(p1));
    }

    @Test
    void everyoneHasBet() {
        BettingRound round = new BettingRound(RoundType.BETTING, 5, new QuestionHandler());
        round.play();
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Dimitris");
        Player p2 = new Player("Chris");
        players.add(p1);
        players.add(p2);
        round.setPlayers(players);
        assertFalse(round.everyoneHasBet());
        round.setBet(p1,250);
        assertFalse(round.everyoneHasBet());
        round.setBet(p2,500);
        assertTrue(round.everyoneHasBet());
    }
}