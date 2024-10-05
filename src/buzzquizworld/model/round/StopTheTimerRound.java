package buzzquizworld.model.round;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;

import java.util.ArrayList;

/**
 * StopTheTimerRound  class represents a round in the game.
 * The player's score is increased by 0.2 times the milliseconds left on the timer if he answers correctly
 * StopTheTimerRound extends Round class.
 * @author Dimitris
 */
public class StopTheTimerRound extends Round {

    private final float POINTS =  0.2F;
    private ArrayList<Player> playersWhoHaveAnswered;
    private int[] timeStamp;

    /**
     * Constructor that creates an instance of a StopTheTimerRound object.
     * @param type             the type of the round
     * @param questionsInRound number of questions in the round
     * @param questionHandler  the question handler
     */
    public StopTheTimerRound(RoundType type, int questionsInRound, QuestionHandler questionHandler){
        super(type, questionsInRound, questionHandler);
    }

    /**
     * Method that initiates the stop-the-timer round
     */
    @Override
    public void play() {
        currentQuestion = questionHandler.pickRandomQuestion();
        questionsAsked ++;
        playersWhoHaveAnswered = new ArrayList<>();
        timeStamp = new int[players.size()];
    }

    /**
     * Method that gets the answer that the player gave and changes his score
     * @param player the player who answered
     * @param answerIndex the answer that the player gave
     * @param ms the milliseconds left on the timer
     * @return true if the answer was correct, false if the answer was wrong
     */
    public boolean playerAnswer(Player player, int answerIndex, int ms){
        if (playersWhoHaveAnswered.contains(player))
            return false;
        playersWhoHaveAnswered.add(player);
        timeStamp[players.indexOf(player)] = ms;
        if (currentQuestion.getCorrectAnswerIndex() == answerIndex){
            player.changeScore(POINTS * ms);
            return true;
        }
        return false;
    }

    /**
     * Method that checks if every player has placed a bet
     * @return true if everyone has placed a bet, false otherwise
     */
    public boolean everyoneHasAnswered(){ return playersWhoHaveAnswered.size() == players.size(); }

    /**
     * Method that returns a list of the players who have answered the question
     * @return ArrayList of the players
     */
    public ArrayList<Player> getPlayersWhoHaveAnswered() { return playersWhoHaveAnswered; }

    /**
     * Method that returns the time left on the timer when the player gave the answer
     * @param playerIndex the index of the player
     * @return the time left on the timer
     */
    public int getPlayerTimeStamp(int playerIndex) { return timeStamp[playerIndex]; }

}
