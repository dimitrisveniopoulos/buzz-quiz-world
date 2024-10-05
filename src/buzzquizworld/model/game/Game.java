package buzzquizworld.model.game;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;
import buzzquizworld.model.round.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Game class represents a quiz game with different rounds.
 * Contains information about the players, the rounds, the number of rounds and the number of questions per round.
 * @author Dimitris
 */
public class Game {
    private static final int NUMBER_OF_ROUNDS = 5;
    private static final int QUESTIONS_PER_ROUND = 5;

    private final int numberOfRounds;
    private final int questionsPerRound;
    private ArrayList<Player> players;
    private static ArrayList<Player> playersArchive;
    private final QuestionHandler questionHandler;
    private Round round;
    private int roundsPlayed;

    /**
     * Constructor that creates an instance of Game object.
     * Number of rounds and number of questions per round are set to the default values.
     */
    public Game(){
        this(NUMBER_OF_ROUNDS, QUESTIONS_PER_ROUND);
    }

    /**
     * Constructor that creates an instance of Game object.
     * Number of rounds and number of questions per round are set to the value of the respective parameters.
     * @param numberOfRounds number of rounds in the game
     * @param questionsPerRound number of questions per round in the game
     */
    public Game(int numberOfRounds, int questionsPerRound){
        this.numberOfRounds = (numberOfRounds >= NUMBER_OF_ROUNDS) ? numberOfRounds : NUMBER_OF_ROUNDS;
        this.questionsPerRound = (questionsPerRound >= QUESTIONS_PER_ROUND) ? questionsPerRound : QUESTIONS_PER_ROUND;
        createPlayerArchive();
        questionHandler = new QuestionHandler();
        roundsPlayed = 0;
    }

    /**
     * @return list of all the players
     */
    public ArrayList<Player> getPlayers () { return players; }

    /**
     * @param newPlayers list of players
     */
    public void setPlayers(ArrayList<Player> newPlayers){
        players = newPlayers;
        for (Player player : players)
            addPlayerToArchive(player);
    }

    /**
     * Method that returns the archive with player's stats
     * @return ArrayList of all the players in the archive
     */
    public ArrayList<Player> getPlayersArchive() { return playersArchive; }

    /**
     * Method that adds a player to the player archive
     * @param player the player to be added in the archive
     */
    public void addPlayerToArchive(Player player){
        boolean exists = false;
        for (Player p: playersArchive){
            if (player.getName().equals(p.getName())){
                int index = players.indexOf(player);
                players.set(index, p);
                exists = true;
            }
        }
        if (!exists)
            playersArchive.add(player);
    }

    /**
     * Method that creates the player archive
     * @return the player archive as ArrayList
     */
    public static ArrayList<Player> createPlayerArchive() {
        String line;
        playersArchive = new ArrayList<>();
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("playerArchive.csv"));
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                float highScore = Float.parseFloat(data[1]);
                int wins = Integer.parseInt(data[2]);
                playersArchive.add(new Player(name,wins,highScore));
            }
            csvReader.close();
        }catch (IOException ignored){
        }
        return playersArchive;
    }

    /**
     * Method that updates the player archive
     * @throws IOException exception from file
     */
    public void updatePlayerArchive() throws IOException {
        FileWriter csvWriter = new FileWriter("playerArchive.csv");

        ArrayList<ArrayList<String>> rows = new ArrayList<>();

        for (Player player : playersArchive){
            ArrayList<String> temp = new ArrayList<>();
            temp.add(player.getName());
            temp.add("" + player.getHighScore());
            temp.add("" + player.getWins());
            rows.add(temp);
        }

        for (ArrayList<String> data : rows) {
            csvWriter.append(String.join(",", data));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    /**
     * Method that creates a new round randomly.
     */
    public void createNewRound(){
        boolean isValid = false;

        if (players.size() > 1 && getRoundsRemaining() == 1){
            round = new Thermometer(RoundType.THERMOMETER, questionsPerRound, questionHandler);
            isValid = true;
        }

        while (!isValid){
            int pickRound = new Random().nextInt(RoundType.values().length);
            switch (RoundType.values()[pickRound]){
                case BETTING:
                    round = new BettingRound(RoundType.BETTING, questionsPerRound, questionHandler);
                    isValid = true;
                    break;
                case CORRECT_ANSWER:
                    round = new CorrectAnswerRound(RoundType.CORRECT_ANSWER, questionsPerRound, questionHandler);
                    isValid = true;
                    break;
                case QUICK_ANSWER:
                    round = new QuickAnswerRound(RoundType.QUICK_ANSWER, questionsPerRound, questionHandler);
                    isValid = (players.size() > 1);
                    break;
                case STOP_THE_TIMER:
                    round = new StopTheTimerRound(RoundType.STOP_THE_TIMER, questionsPerRound, questionHandler);
                    isValid = true;
                    break;
            }
        }

        round.setPlayers(players);
        roundsPlayed++;
   }

    /**
     * Method that ends the game, declares the winner and updates the player archive
     */
   public void endGame(){
        Player winner = players.get(0);
        for (Player player: players){
            if (player.getScore() > winner.getScore())
                winner = player;
            player.setHighScore(player.getScore());
        }
        if (players.size() > 1)
            winner.setWins(winner.getWins() + 1);

       try {
           updatePlayerArchive();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    /**
     * Method that returns the number of rounds played so far
     * @return number of rounds played so far
     */
    public int getRoundsPlayed() { return roundsPlayed; }

    /**
     * Method that returns the number of rounds remaining
     * @return number of rounds remaining
     */
    public int getRoundsRemaining(){ return numberOfRounds - roundsPlayed; }

    /**
     * Method that returns the current game round
     * @return current Round
     */
    public Round getRound() { return round; }

    /**
     * Method that returns the total number of rounds in the game
     * @return total number of rounds in the game
     */
    public int getNumberOfRounds() { return numberOfRounds; }

}
