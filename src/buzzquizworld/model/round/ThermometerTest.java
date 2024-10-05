package buzzquizworld.model.round;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;
import buzzquizworld.model.question.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ThermometerTest extends RoundTest{

    @Override
    Round getObject() { return new Thermometer(RoundType.THERMOMETER,50,new QuestionHandler()); }

    @Test
    void play() {
        Thermometer round = new Thermometer(RoundType.THERMOMETER,50,new QuestionHandler());
        round.setPlayers(new ArrayList<>());
        for (int i = 1; i <= 5; i ++){
            round.play();
            assertNotNull(round.getQuestionPoll());
        }
    }

    @Test
    void getQuestionPoll() {
        Thermometer round = new Thermometer(RoundType.THERMOMETER,50,new QuestionHandler());
        round.setPlayers(new ArrayList<>());
        round.play();
        assertEquals(round.getQuestionPoll().size(), 10);
        for (Question q : round.getQuestionPoll())
            assertNotNull(q);
    }

    @Test
    void getNextQuestionForPlayer() {
        Thermometer round = new Thermometer(RoundType.THERMOMETER,50,new QuestionHandler());
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Jim");
        players.add(p1);
        round.setPlayers(players);
        round.play();

        for (int i = 0; i < 10; i ++){
            Question q = round.getNextQuestionForPlayer(p1);
            assertNotNull(q);
            round.playerAnswer(p1,1);
        }
    }

    @Test
    void getCorrectAnswersByPlayer() {
        Thermometer round = new Thermometer(RoundType.THERMOMETER,50,new QuestionHandler());
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Jim");
        players.add(p1);
        round.setPlayers(players);
        round.play();
        for (int i = 1; i <= 5; i ++){
            Question q = round.getNextQuestionForPlayer(p1);
            round.playerAnswer(p1,q.getCorrectAnswerIndex());
            assertEquals(i, round.getCorrectAnswersByPlayer(p1));
        }
    }

    @Test
    void getTotalQuestionsAskedToPlayer() {
        Thermometer round = new Thermometer(RoundType.THERMOMETER,50,new QuestionHandler());
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Jim");
        players.add(p1);
        round.setPlayers(players);
        round.play();
        for (int i = 1; i <= 10; i ++){
            Question q = round.getNextQuestionForPlayer(p1);
            round.playerAnswer(p1,0);
            assertEquals(i, round.getTotalQuestionsAskedToPlayer(p1));
        }
    }

    @Test
    void playerAnswer() {
        Thermometer round = new Thermometer(RoundType.THERMOMETER,50,new QuestionHandler());
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Jim");
        Player p2 = new Player("John");
        players.add(p1);
        players.add(p2);
        round.setPlayers(players);
        round.play();

        float initialScore1 = p1.getScore();
        float initialScore2 = p2.getScore();

        for (int i = 1; i <=5; i ++){
            Question q = round.getNextQuestionForPlayer(p1);
            round.playerAnswer(p1,q.getCorrectAnswerIndex());
        }
        assertEquals(initialScore1 + 5000, p1.getScore());

        for (int i = 1; i <=5; i ++){
            Question q = round.getNextQuestionForPlayer(p1);
            round.playerAnswer(p2,q.getCorrectAnswerIndex());
        }
        assertEquals(initialScore2, p2.getScore());
    }

    @Test
    void playerIsDone() {
        Thermometer round = new Thermometer(RoundType.THERMOMETER,50,new QuestionHandler());
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Jim");
        Player p2 = new Player("John");
        players.add(p1);
        players.add(p2);
        round.setPlayers(players);
        round.play();
        for (int i = 0; i < 10; i ++)
            round.playerAnswer(p1,1);
        assertTrue(round.playerIsDone(p1));
        for (int i = 0; i < 5; i ++)
            round.playerAnswer(p2,round.getNextQuestionForPlayer(p2).getCorrectAnswerIndex());
        assertTrue(round.playerIsDone(p1));
    }

    @Test
    void roundIsDone() {
        Thermometer round = new Thermometer(RoundType.THERMOMETER,50,new QuestionHandler());
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("Jim");
        Player p2 = new Player("John");
        players.add(p1);
        players.add(p2);
        round.setPlayers(players);
        round.play();

        assertEquals(round.playerIsDone(p1) && round.playerIsDone(p2),round.roundIsDone());

        for (int i = 0; i < 10; i ++)
            round.playerAnswer(p1,1);

        assertEquals(round.playerIsDone(p1) && round.playerIsDone(p2),round.roundIsDone());

        for (int i = 0; i < 5; i ++)
            round.playerAnswer(p2,round.getNextQuestionForPlayer(p2).getCorrectAnswerIndex());

        assertEquals(round.playerIsDone(p1) && round.playerIsDone(p2),round.roundIsDone());
    }
}