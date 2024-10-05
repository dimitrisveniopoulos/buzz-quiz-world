package buzzquizworld.model.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getName() {
        Player p = new Player("Dimitris");
        assertEquals("Dimitris",p.getName());
    }

    @Test
    void setName() {
        Player p = new Player("Dimitris");
        p.setName("Chris");
        assertEquals("Chris",p.getName());
    }

    @Test
    void getScore() {
        Player p = new Player("Dimitris");
        assertEquals(0,p.getScore());
    }

    @Test
    void changeScore() {
        Player p = new Player("Dimitris");
        p.changeScore(15.6F);
        assertEquals(15.6,p.getScore(),0.0001);
    }

    @Test
    void getWins() {
        int wins = 13;
        Player p = new Player("Dimitris", wins,1304);
        assertEquals(wins, p.getWins());
    }

    @Test
    void setWins() {
        int wins = 13;
        Player p = new Player("Dimitris", wins, 123);
        p.setWins(67);
        assertEquals(67, p.getWins());
    }

    @Test
    void getHighScore() {
        float highScore = 1367.3F;
        Player p = new Player("Dimitris",2, highScore);
        assertEquals(highScore, p.getHighScore());
    }

    @Test
    void setHighScore() {
        float highScore = 45.6F;
        Player p = new Player("Chris", 13, highScore);
        p.setHighScore(140.56F);
        assertEquals(140.56, p.getHighScore(),0.0001);
    }

}