package buzzquizworld.model.round;

import buzzquizworld.handler.QuestionHandler;
import buzzquizworld.model.player.Player;
import buzzquizworld.model.question.Question;

import java.util.ArrayList;

/**
 * Round is an abstract class that represents a round in the quiz.
 * Each round has a list of players, a question handler (via which you can access the questions) and a certain number of questions to be asked.
 * @author Dimtris
 */
public abstract class Round {

    protected RoundType type;
    protected QuestionHandler questionHandler;
    protected int questionsInRound;
    protected Question currentQuestion;
    protected int questionsAsked;
    protected ArrayList<Player> players;

    /**
     * Constructor that creates an instance of a Round object
     * @param type the type of the round
     * @param questionsInRound number of question in the round
     * @param questionHandler the question handler of the round
     */
    Round(RoundType type, int questionsInRound, QuestionHandler questionHandler){
        this.type = type;
        this.questionsInRound = questionsInRound;
        this.questionHandler = questionHandler;
        questionsAsked = 0;
    }

    /**
     * Abstract method that initiates the round.
     * Qustions are asked and answers are given by the players
     */
    public abstract void play();

    /**
     * Method that sets the players of the round to the given list
     * @param players ArrayList of the players
     */
    public void setPlayers(ArrayList<Player> players) { this.players = players; }

    /**
     * Method that returns the type of the round
     * @return the type of the round
     */
    public RoundType getType() { return type; }

    /**
     * Method that returns the current questions in the round
     * @return current question in the round
     */
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    /**
     * Method that returns the number of the questions that remain to be asked
     * @return number of questions remaining
     */
    public int getQuestionsRemaining(){
        return questionsInRound - questionsAsked;
    }

}
