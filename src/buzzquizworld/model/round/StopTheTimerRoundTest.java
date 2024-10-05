package buzzquizworld.model.round;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;
import buzzquizworld.model.question.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StopTheTimerRoundTest extends RoundTest{

    @Override
    Round getObject() { return new StopTheTimerRound(RoundType.STOP_THE_TIMER,5,new QuestionHandler()); }

    @Test
    void play() {
        StopTheTimerRound round = new StopTheTimerRound(RoundType.STOP_THE_TIMER,5,new QuestionHandler());
        round.setPlayers(new ArrayList<>());
        for (int i = 1; i <= 5; i ++){
            round.play();
            assertNotNull(round.getCurrentQuestion());
            assertEquals(5 - i, round.getQuestionsRemaining());
        }
    }

    @Test
    void playerAnswer() {
        StopTheTimerRound round = new StopTheTimerRound(RoundType.STOP_THE_TIMER,5,new QuestionHandler());
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Dimitris");
        players.add(p1);
        round.setPlayers(players);
        float initialScore = p1.getScore();
        round.play();
        Question q = round.currentQuestion;
        int answer = q.getCorrectAnswerIndex();
        int timestamp = 3600;
        assertTrue(round.playerAnswer(p1,answer,timestamp));
        assertEquals(initialScore + timestamp * 0.2 ,p1.getScore());
    }

    @Test
    void everyoneHasAnswered() {
        StopTheTimerRound round = new StopTheTimerRound(RoundType.STOP_THE_TIMER,5,new QuestionHandler());
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Dimitris");
        Player p2 = new Player("Chris");
        players.add(p1);
        players.add(p2);
        round.setPlayers(players);
        round.play();
        assertFalse(round.everyoneHasAnswered());
        round.playerAnswer(p1,1,3140);
        assertFalse(round.everyoneHasAnswered());
        round.playerAnswer(p2, 2,1104);
        assertTrue(round.everyoneHasAnswered());
    }

    @Test
    void getPlayersWhoHaveAnswered() {
        StopTheTimerRound round = new StopTheTimerRound(RoundType.STOP_THE_TIMER,5,new QuestionHandler());
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Dimitris");
        Player p2 = new Player("Chris");
        players.add(p1);
        players.add(p2);
        round.setPlayers(players);
        round.play();
        round.playerAnswer(p1,1,3140);
        round.playerAnswer(p2, 2,1104);
        for (Player p : players)
            assertTrue(round.getPlayersWhoHaveAnswered().contains(p));
    }

    @Test
    void getPlayerTimeStamp() {
        StopTheTimerRound round = new StopTheTimerRound(RoundType.STOP_THE_TIMER,5,new QuestionHandler());
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Dimitris");
        players.add(p1);
        round.setPlayers(players);
        round.play();
        int timestamp = 3140;
        round.playerAnswer(p1,1,timestamp);
        assertEquals(timestamp, round.getPlayerTimeStamp(0));
    }
}