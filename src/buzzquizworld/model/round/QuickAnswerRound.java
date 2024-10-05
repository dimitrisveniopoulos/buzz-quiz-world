package buzzquizworld.model.round;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;

import java.util.ArrayList;

/**
 * QuickAnswerRound class represents a round in the game.
 * The player's score is increased by 1000 points if he is the first to answer correctly
 * or by 500 if he is the second to answer correctly.
 * QuickAnswerRound extends Round class.
 * @author Dimitris
 */
public class QuickAnswerRound extends Round{

    private static final int FIRST_ANSWER_POINTS = 1000;
    private static final int SECOND_ANSWER_POINTS = 500;

    private ArrayList<Player> playersWhoHaveAnswered;
    private int numberOfCorrectAnswers;

    /**
     * Constructor that creates an instance of a QuickAnswerRound object.
     * @param type             the type of the round
     * @param questionsInRound number of questions in the round
     * @param questionHandler  the question handler
     */
    public QuickAnswerRound(RoundType type, int questionsInRound, QuestionHandler questionHandler){
        super(type, questionsInRound, questionHandler);
    }

    /**
     * Method that initiates the quick answer round
     */
    @Override
    public void play() {
        currentQuestion = questionHandler.pickRandomQuestion();
        questionsAsked ++;
        playersWhoHaveAnswered = new ArrayList<>();
        numberOfCorrectAnswers = 0;
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
            numberOfCorrectAnswers ++;
            if (numberOfCorrectAnswers == 1)
                player.changeScore(FIRST_ANSWER_POINTS);
            else
                player.changeScore(SECOND_ANSWER_POINTS);
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
