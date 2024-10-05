package buzzquizworld.model.round;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;

import java.util.ArrayList;

/**
 * CorrectAnswerRound class represents a round in the game.
 * The player's score is increased by a certain amount of points if he answers correctly, whereas it remains the same if the answer is incorrect.
 * CorrectRound extends Round class.
 * @author Dimitris
 */
public class CorrectAnswerRound extends Round{

    private static final int CORRECT_ANSWER_POINTS = 1000;
    private ArrayList<Player> playersWhoHaveAnswered;

    /**
     * Constructor that creates an instance of a CorrectAnswerRound object.
     * @param type             the type of the round
     * @param questionsInRound number of questions in the round
     * @param questionHandler  the question handler
     */
    public CorrectAnswerRound(RoundType type, int questionsInRound, QuestionHandler questionHandler) {
        super(type, questionsInRound, questionHandler);
    }

    /**
     * Method that initiates the correct answer round.
     */
    @Override
    public void play() {
        currentQuestion = questionHandler.pickRandomQuestion();
        questionsAsked ++;
        playersWhoHaveAnswered = new ArrayList<>();

    }

    /**
     * Method that gets the answer that the player gave and changes his score
     * @param player the player who answered
     * @param answerIndex the answer that the player gave
     * @return true if the answer was correct, false if the answer was wrong
     */
    public boolean playerAnswer(Player player, int answerIndex){
        if (playersWhoHaveAnswered.contains(player))
            return false;
        playersWhoHaveAnswered.add(player);
        if (currentQuestion.getCorrectAnswerIndex() == answerIndex){
            player.changeScore(CORRECT_ANSWER_POINTS);
            return true;
        }
        return false;
    }


    /**
     * Method that checks if every player has placed a bet
     * @return true if everyone has placed a bet, false otherwise
     */
    public boolean everyoneHasAnswered(){ return playersWhoHaveAnswered.size() == players.size(); }

}
