package buzzquizworld.model.round;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;
import buzzquizworld.model.question.Question;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Thermometer  class represents a round in the game.
 * The first player to answer correctly 5 answers gets 5000 points
 * Thermometer extends Round class.
 * @author Dimitris
 */
public class Thermometer extends Round {

    private static final int QUESTIONS_IN_POLL = 10;
    private static final int CORRECT_ANSWERS_MUST_BE = 5;
    private static final float POINTS = 5000F;

    private final ArrayList<Question> questionPoll;
    private final HashMap<Player, Integer> questionsAnsweredByPlayer;
    private final HashMap<Player, Integer> correctAnswersByPlayer;
    private Player winner;


    /**
     * Constructor that creates an instance of a Thermometer object.
     * @param type             the type of the round
     * @param questionsInRound number of questions in the round
     * @param questionHandler  the question handler
     */
    public Thermometer(RoundType type, int questionsInRound, QuestionHandler questionHandler) {
        super(type, questionsInRound, questionHandler);
        questionPoll = new ArrayList<>();
        questionsAnsweredByPlayer = new HashMap<>();
        correctAnswersByPlayer = new HashMap<>();
        winner = null;
    }

    /**
     * Method that initiates the stop-the-timer round
     */
    @Override
    public void play() {
        for (int i = 0; i < QUESTIONS_IN_POLL; i ++)
            questionPoll.add(questionHandler.pickRandomQuestion());

        for (Player player : players){
            questionsAnsweredByPlayer.put(player ,0);
            correctAnswersByPlayer.put(player, 0);
        }
    }

    /**
     * Method that returns the questions that will be asked to the players
     * @return ArrayList of the questions
     */
    public ArrayList<Question> getQuestionPoll() { return questionPoll; }

    /**
     * Method that returns the next questions for the given player
     * @param player the player
     * @return the next question that will be asked to the player
     */
    public Question getNextQuestionForPlayer(Player player){
        int index = questionsAnsweredByPlayer.get(player);
        return questionPoll.get(index);
    }

    /**
     * Method that returns the number of correct answers given by the player
     * @param player the player
     * @return the number of correct answers given by the player
     */
    public int getCorrectAnswersByPlayer(Player player) { return correctAnswersByPlayer.get(player); }

    /**
     * Method that returns the total number of questions asked to the player
     * @param player the player
     * @return the total number of questions asked to the player
     */
    public int getTotalQuestionsAskedToPlayer(Player player) { return questionsAnsweredByPlayer.get(player); }

    /**
     * Method that gets the answer that the player gave and changes his score
     * @param player the player who answered
     * @param answerIndex the answer that the player gave
     * @return true if the answer was correct, false if the answer was wrong or he has already answered all the questions
     */
    public boolean playerAnswer(Player player, int answerIndex){
        if (playerIsDone(player) || roundIsDone())
            return false;

        int index = questionsAnsweredByPlayer.get(player);
        Question question = questionPoll.get(index);
        questionsAnsweredByPlayer.put(player, index + 1);

        int correctAnswers;
        if (question.getCorrectAnswerIndex() == answerIndex){
            correctAnswers = correctAnswersByPlayer.get(player) + 1;
            correctAnswersByPlayer.put(player, correctAnswers);
            if (correctAnswers == CORRECT_ANSWERS_MUST_BE){
                player.changeScore(POINTS);
                winner = player;
            }
            return true;
        }
        return false;
    }

    /**
     * Method that checks if a player is done with the questions
     * @param player the player
     * @return true if the player is done, false if he is still answering questions
     */
    public boolean playerIsDone(Player player){
        return questionsAnsweredByPlayer.get(player) == 10 || correctAnswersByPlayer.get(player) == CORRECT_ANSWERS_MUST_BE;
    }

    /**
     * Method that checks if all the players are done
     * @return true if the round is over, false otherwise
     */
    public boolean roundIsDone(){
        if (winner != null)
            return true;

        for (Player player : players){
            if (!playerIsDone(player))
                return false;
        }
        return true;
    }
}
