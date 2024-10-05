package buzzquizworld.model.player;

/**
 * Player class represents a player in the game.
 * Contains information for his/her name, current score, high score, games played and total wins.
 * @author Chris
 */
public class Player {
    private String name;
    private float score;
    private float highScore;
    private int wins;

    /**
     * Constructor that creates an instance of a Player object. All numerical fields are set to 0.
     * Name is set to the value of the respective parameter
     * @param name the name of the player
     */
    public Player(String name){
        this(name,0,0);
    }

    /**
     * Constructor that creates an instance of a Player object. All fields are set to the respective parameter values.
     * @param name name of the player
     * @param wins total wins of the player
     * @param highScore player's high score
     */
    public Player(String name, int wins, float highScore){
        this.name = name;
        this.wins  = wins;
        this.highScore = highScore;
        score = 0;
    }

    /**
     * @return name of the player
     */
    public String getName(){
        return name;
    }

    /**
     * @param name name to which the player's name is set to
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @return current score
     */
    public float getScore(){ return score; }

    /**
     * Method that adds the value of points parameter to the player's score.
     * @param points points that will be added to player's current score
     */
    public void changeScore(float points){
        score += points;
    }

    /**
     * @return total wins
     */
    public int getWins(){
        return wins;
    }

    /**
     * @param wins value to which total wins are set to
     */
    public void setWins(int wins){ this.wins = wins; }

    /**
     * @return player's high score
     */
    public float getHighScore(){ return highScore; }

    /**
     * @param score value to which player's high score is set to
     */
    public void setHighScore(float score){
        if (score > highScore)
            highScore = score;
    }

}