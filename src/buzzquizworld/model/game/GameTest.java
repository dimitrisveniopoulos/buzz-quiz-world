package buzzquizworld.model.game;

import buzzquizworld.model.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void getPlayers() {
        Game g = new Game();
        Player p1 = new Player("Dimitris");
        Player p2 = new Player("Giorgos");
        Player p3 = new Player("Giannis");
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        g.setPlayers(players);
        assertEquals(players, g.getPlayers());
    }

    @Test
    void setPlayers() {
        Game g = new Game();
        Player p1 = new Player("Dimitris");
        Player p2 = new Player("Giorgos");
        Player p3 = new Player("Giannis");
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        g.setPlayers(players);
        assertEquals(players, g.getPlayers());
    }

    @Test
    void getPlayersArchive() {
        Game g = new Game();
        Player p1 = new Player("Dimitris");
        Player p2 = new Player("Giorgos");
        Player p3 = new Player("Giannis");
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        g.setPlayers(players);
        for (Player p : players)
            assertTrue(g.getPlayersArchive().contains(p));
    }

    @Test
    void addPlayerToArchive() {
        Game g = new Game();
        Player p = new Player("testing player", 13, 150.4F);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p);
        int initialSize = g.getPlayersArchive().size();
        g.setPlayers(players); //also adds player to archive
        assertEquals(initialSize + 1, g.getPlayersArchive().size());

        initialSize = g.getPlayersArchive().size();
        g.addPlayerToArchive(p);
        assertEquals(initialSize, g.getPlayersArchive().size());
    }

    @Test
    void createPlayerArchive() {
        Game g = new Game();
        Game.createPlayerArchive();
        assertNotNull(g.getPlayersArchive());
    }

    @Test
    void updatePlayerArchive() {

        Game g = new Game();
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player("khl423lhj1243");
        p1.changeScore(1500);
        Player p2 = new Player("Christos");
        p2.changeScore(1000);

        players.add(p1);
        players.add(p2);
        g.setPlayers(players);

        float p1InitialHighScore = p1.getHighScore(); // = 0 since it's his first game
        g.endGame(); //endgame method calls updatePlayerArchive

        Game g1 = new Game();
        Game.createPlayerArchive();

        float p1FinalHighScore = 0;
        for (Player p : g1.getPlayersArchive()){
            if (p.getName().equals(p1.getName()))
                p1FinalHighScore = p.getHighScore();
        }

        assertEquals(p1InitialHighScore + 1500, p1FinalHighScore); //
    }

    @Test
    void createNewRound() {
        Game g = new Game();
        g.setPlayers(new ArrayList<>());
        g.createNewRound();
        assertNotNull(g.getRound());
    }

    @Test
    void endGame() {
        Game g = new Game();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Giannis"));
        players.add(new Player("Giorgos"));
        players.get(0).changeScore(1500);
        players.get(1).changeScore(1000);

        g.setPlayers(players);

        int p1InitialWins = players.get(0).getWins();
        g.endGame();
        assertEquals(p1InitialWins + 1, players.get(0).getWins());
    }

    @Test
    void getRoundsPlayed() {
        Game g = new Game();
        g.setPlayers(new ArrayList<>());
        for (int i = 1; i <= g.getNumberOfRounds(); i ++){
            g.createNewRound();
            assertEquals(g.getRoundsPlayed(), i);
        }
    }

    @Test
    void getRoundsRemaining() {
        Game g = new Game();
        g.setPlayers(new ArrayList<>());
        for (int i = 1; i <= g.getNumberOfRounds(); i ++){
            g.createNewRound();
            assertEquals(g.getRoundsRemaining(), g.getNumberOfRounds() - i);
        }
    }

    @Test
    void getRound() {
        Game g = new Game();
        g.setPlayers(new ArrayList<>());
        g.createNewRound();
        assertNotNull(g.getRound());
    }

    @Test
    void getNumberOfRounds() {
        int numberOfRounds = 7;
        Game g = new Game(numberOfRounds, 6);
        assertEquals(numberOfRounds,g.getNumberOfRounds());

        numberOfRounds = 3;
        g = new Game(numberOfRounds, 6);
        assertEquals(g.getNumberOfRounds(),5);
    }
}