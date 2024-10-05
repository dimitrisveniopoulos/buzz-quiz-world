package buzzquizworld.model.round;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * BettingRound class represents a round in which the player bets a certain amount of points for each question.
 * The player's score is increased or decreased by the bet amount when he/she answers correctly or incorrectly respectively.
 * BettingRound extends Round class.
 * @author Dimitris
 */
public class BettingRound extends Round {

    private ArrayList<Player> playersWhoHaveAnswered;
    private HashMap<Player, Integer> playersWhoHaveBet;

    /**
     * Constructor that creates an instance of a BettingRound object.
     * @param type             the type of the round
     * @param questionsInRound number of questions in the round
     * @param questionHandler  the question handler
     */
    public BettingRound(RoundType type, int questionsInRound, QuestionHandler questionHandler) {
        super(type, questionsInRound, questionHandler);
    }

    /**
     * Method that initiates the betting round.
     */
    @Override
    public void play() {
        currentQuestion = questionHandler.pickRandomQuestion();
        questionsAsked ++;
        playersWhoHaveAnswered = new ArrayList<>();
        playersWhoHaveBet = new HashMap<>();
    }

    /**
     * Method that gets the answer that the player gave and changes his score according to the bet
     * @param player the player who answered
     * @param answerIndex the answer that the player gave
     * @param bet the bet that the player placed
     * @return true if the answer was correct, false if the answer was wrong
     */
    public boolean playerAnswer(Player player, int answerIndex, int bet) {
        if (playersWhoHaveAnswered.contains(player))
            return false;
        playersWhoHaveAnswered.add(player);
        if (currentQuestion.getCorrectAnswerIndex() == answerIndex){
            player.changeScore(bet);
            return true;
        }
        player.changeScore(-bet);
        return false;
    }

    /**
     * Method that checks if every player has answered the question
     * @return true if everyone has answered, false otherwise
     */
    public boolean everyoneHasAnswered(){ return playersWhoHaveAnswered.size() == players.size(); }

    /**
     * Method that sets the bet that the player placed
     * @param player the player who placed the bet
     * @param bet the bet placed by the player
     */
    public void setBet(Player player, int bet){
        if (!playersWhoHaveBet.containsKey(player))
            playersWhoHaveBet.put(player, bet);
    }

    /**
     * Method that returns the bet that the player placed
     * @param player the player whose bet will be returned
     * @return the bet that the player placed
     */
    public int getBet(Player player){
        if (playersWhoHaveBet.containsKey(player))
            return playersWhoHaveBet.get(player);
        return 0;
    }

    /**
     * Method that checks if every player has placed a bet
     * @return true if everyone has placed a bet, false otherwise
     */
    public boolean everyoneHasBet() { return playersWhoHaveBet.size() == players.size(); }
}
